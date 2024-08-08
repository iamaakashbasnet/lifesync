import { Burger, Group, Image } from '@mantine/core';

import logo from 'assets/logo.png';

const Header = ({
  opened,
  toggle,
}: {
  opened: boolean;
  toggle: () => void;
}) => {
  return (
    <>
      <Group h="100%" px="md">
        <Burger opened={opened} onClick={toggle} hiddenFrom="sm" size="sm" />
        LifeSync
        <Image width="auto" height={30} src={logo} />
      </Group>
    </>
  );
};

export default Header;
