import React, { lazy } from 'react';
import { routerTypes } from 'types/routerTypes';

const Home = lazy(() => import('pages/Home'));
const Login = lazy(() => import('pages/Login'));
const Signup = lazy(() => import('pages/Signup'));
const Dashboard = lazy(() => import('pages/Dashboard'));
const AddTodo = lazy(() => import('pages/Todo/AddTodo'));
const ViewTodo = lazy(() => import('pages/Todo/ViewTodo'));
const AddNote = lazy(() => import('pages/Note/AddNote'));
const ListNote = lazy(() => import('pages/Note/ListNote'));
const ViewNote = lazy(() => import('pages/Note/ViewNote'));

export const routes: routerTypes[] = [
  { title: 'Home', path: '', element: React.createElement(Home) },
  { title: 'Login', path: '/login', element: React.createElement(Login) },
  { title: 'Signup', path: '/signup', element: React.createElement(Signup) },
];

export const dashboardRoutes: routerTypes[] = [
  { title: 'Dashboard', path: '', element: React.createElement(Dashboard) },
  {
    title: 'View Todo',
    path: 'add-todo',
    element: React.createElement(AddTodo),
  },
  {
    title: 'Add Todo',
    path: 'view-todo',
    element: React.createElement(ViewTodo),
  },
  {
    title: 'Add Note',
    path: 'add-note',
    element: React.createElement(AddNote),
  },
  {
    title: 'List Note',
    path: 'list-note',
    element: React.createElement(ListNote),
  },
  {
    title: 'View Note',
    path: 'view-note',
    element: React.createElement(ViewNote),
  },
];
