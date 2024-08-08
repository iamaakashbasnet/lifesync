import { AppShell, Burger, Group } from '@mantine/core';
import { Outlet } from 'react-router-dom';
import { useDisclosure } from '@mantine/hooks';

import Header from './components/Header';
import Footer from './components/Footer';
import Sidebar from './components/Sidebar';
import Aside from './components/Aside';

const Layout = () => {
  const [opened, { toggle }] = useDisclosure();

  return (
    <AppShell
      layout="alt"
      header={{ height: 60 }}
      footer={{ height: 60 }}
      navbar={{ width: 300, breakpoint: 'sm', collapsed: { mobile: !opened } }}
      aside={{
        width: 300,
        breakpoint: 'md',
        collapsed: { desktop: false, mobile: true },
      }}
      padding="md"
    >
      <AppShell.Header>
        <Header opened={opened} toggle={toggle} />
      </AppShell.Header>

      <AppShell.Navbar p="md">
        <Group>
          <Burger opened={opened} onClick={toggle} hiddenFrom="sm" size="sm" />
        </Group>
        <Sidebar />
      </AppShell.Navbar>

      <AppShell.Main>
        <Outlet />
      </AppShell.Main>

      <AppShell.Aside p="md">
        <Aside />
      </AppShell.Aside>

      <AppShell.Footer p="md">
        <Footer />
      </AppShell.Footer>
    </AppShell>
  );
};

export default Layout;
