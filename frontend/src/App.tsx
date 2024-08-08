import React from 'react';
import { BrowserRouter } from 'react-router-dom';

import FallbackLoading from 'components/FallbackLoading';
import CustomRouter from 'routers/router';

function App() {
  return (
    <BrowserRouter>
      <React.Suspense fallback={<FallbackLoading />}>
        <CustomRouter />
      </React.Suspense>
    </BrowserRouter>
  );
}

export default App;
