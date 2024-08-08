import React, { lazy } from 'react';
import { routerTypes } from 'types/routerTypes';

const Home = lazy(() => import('pages/Home'));
const Login = lazy(() => import('pages/Login'));
const Signup = lazy(() => import('pages/Signup'));
const Dashboard = lazy(() => import('pages/Dashboard'));

export const routes: routerTypes[] = [
  { title: 'Home', path: '', element: React.createElement(Home) },
  { title: 'Login', path: '/login', element: React.createElement(Login) },
  { title: 'Signup', path: '/signup', element: React.createElement(Signup) },
];

export const dashboardRoutes: routerTypes[] = [
  { title: 'Home', path: '', element: React.createElement(Dashboard) },
];
