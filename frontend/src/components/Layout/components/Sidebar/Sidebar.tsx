import { NavLink } from '@mantine/core';
import { IconGauge, IconNote } from '@tabler/icons-react';

const Navbar = () => {
  return (
    <>
      <NavLink
        href="#required-for-focus"
        label="Todo"
        leftSection={<IconGauge size="1rem" stroke={1.5} />}
        childrenOffset={28}
        fw="bold"
        defaultOpened
      >
        <NavLink href="#required-for-focus" label="View todos" />
        <NavLink label="Add todos" href="#required-for-focus" />
      </NavLink>

      <NavLink
        href="#required-for-focus"
        label="Notes"
        leftSection={<IconNote size="1rem" stroke={1.5} />}
        childrenOffset={28}
        fw="bold"
      >
        <NavLink label="View notes" href="#required-for-focus" />
        <NavLink label="Add notes" href="#required-for-focus" />
      </NavLink>
    </>
  );
};

export default Navbar;
