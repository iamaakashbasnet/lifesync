import { NavLink } from '@mantine/core';
import { IconGauge, IconNote } from '@tabler/icons-react';
import { Link } from 'react-router-dom';

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
        <NavLink component={Link} to="view-todo" label="View todos" />
        <NavLink component={Link} to="add-todo" label="Add todos" />
      </NavLink>

      <NavLink
        href="#required-for-focus"
        label="Notes"
        leftSection={<IconNote size="1rem" stroke={1.5} />}
        childrenOffset={28}
        fw="bold"
      >
        <NavLink component={Link} to="list-note" label="List notes" />
        <NavLink component={Link} to="add-note" label="Add notes" />
      </NavLink>
    </>
  );
};

export default Navbar;
