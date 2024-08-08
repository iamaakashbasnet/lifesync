import React, { lazy } from 'react';
import { routerTypes } from 'types/routerTypes';

const Home = lazy(() => import('pages/Home'));

export const routes: routerTypes[] = [
  { title: 'Home', path: '', element: React.createElement(Home) },
];
