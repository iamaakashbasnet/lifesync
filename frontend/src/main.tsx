import React from 'react';
import ReactDOM from 'react-dom/client';
import { createTheme, MantineProvider } from '@mantine/core';
import { Provider } from 'react-redux';
import '@mantine/core/styles.css';
import axios from 'axios';

import App from './App.tsx';
import './index.css';
import '@mantine/tiptap/styles.css';
import { store } from 'state/store.ts';

const theme = createTheme({});
axios.defaults.headers.post['Content-Type'] = 'application/json';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Provider store={store}>
      <MantineProvider theme={theme}>
        <App />
      </MantineProvider>
    </Provider>
  </React.StrictMode>
);
