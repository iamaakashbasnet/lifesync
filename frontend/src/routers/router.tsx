import { lazy } from 'react';
import { Route, Routes } from 'react-router-dom';

import { routerTypes } from 'types/routerTypes';
import { routes, dashboardRoutes } from 'routers/paths';
import DashboardLayout from 'components/Layout';
import PrivateRoutes from 'utils/PrivateRoutes';

const Error404 = lazy(() => import('pages/Error404'));

const CustomRouter = () => {
  return (
    <Routes>
      {routes.map(({ path, title, element }: routerTypes) => (
        <Route key={title} path={`${path}`} element={element} />
      ))}

      <Route path="dashboard" element={<DashboardLayout />}>
        <Route element={<PrivateRoutes />}>
          {dashboardRoutes.map(({ path, title, element }: routerTypes) => (
            <Route key={title} path={`${path}`} element={element} />
          ))}
        </Route>
      </Route>

      {/* Error 404 */}
      <Route path="*" element={<Error404 />} />
    </Routes>
  );
};

export default CustomRouter;
