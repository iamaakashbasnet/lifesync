import React, { useEffect } from 'react';
import { BrowserRouter } from 'react-router-dom';
import { useDispatch } from 'react-redux';

import FallbackLoading from 'components/FallbackLoading';
import CustomRouter from 'routers/router';
import { reAuthAsync } from 'state/user/authSlice';
import { AppDispatch } from 'state/store';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ToastContainer } from 'react-toastify';

function App() {
  const queryClient = new QueryClient();
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    void dispatch(reAuthAsync());

    // Create a Blob object with the worker logic
    const workerBlob = new Blob([
      `
        setInterval(() => {
          self.postMessage('dispatch reAuthAsync');
        }, 4 * 60 * 1000); // 4 minutes in milliseconds
      `,
    ]);

    const worker = new Worker(URL.createObjectURL(workerBlob));

    // Listen for messages from the worker
    worker.onmessage = (event) => {
      if (event.data === 'dispatch reAuthAsync') {
        // Dispatch reAuthAsync when message received
        void dispatch(reAuthAsync());
      }
    };

    // Clean up on unmount
    return () => {
      worker.terminate(); // Terminate the worker when the component is unmounted
    };
  }, [dispatch]);
  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <React.Suspense fallback={<FallbackLoading />}>
          <CustomRouter />
        </React.Suspense>
      </BrowserRouter>
      <ToastContainer
        position="bottom-right"
        autoClose={5000}
        hideProgressBar
        newestOnTop
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
    </QueryClientProvider>
  );
}

export default App;
