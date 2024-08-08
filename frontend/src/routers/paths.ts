import React, { lazy } from 'react';
import { routerTypes } from 'types/routerTypes';

const Home = lazy(() => import('pages/Home'));
const Login = lazy(() => import('pages/Login'));

export const routes: routerTypes[] = [
  { title: 'Home', path: '', element: React.createElement(Home) },
  { title: 'Login', path: '/login', element: React.createElement(Login) },
];
