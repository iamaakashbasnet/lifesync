import { lazy } from 'react';
import { Route, Routes } from 'react-router-dom';

import { routerTypes } from 'types/routerTypes';
import { routes } from 'routers/paths';

const Error404 = lazy(() => import('pages/Error404'));

const CustomRouter = () => {
  return (
    <Routes>
      {routes.map(({ path, title, element }: routerTypes) => (
        <Route key={title} path={`${path}`} element={element} />
      ))}

      {/* Error 404 */}
      <Route path="*" element={<Error404 />} />
    </Routes>
  );
};

export default CustomRouter;
