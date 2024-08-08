import { List, ThemeIcon, rem } from '@mantine/core';
import { IconCircleCheck, IconCircleDashed } from '@tabler/icons-react';

const Aside = () => {
  return (
    <>
      <h3>Latest Updates</h3>
      <List
        spacing="xs"
        size="sm"
        center
        icon={
          <ThemeIcon color="teal" size={24} radius="xl">
            <IconCircleCheck style={{ width: rem(16), height: rem(16) }} />
          </ThemeIcon>
        }
      >
        <List.Item>Todo features</List.Item>
        <List.Item>Notes features</List.Item>
        <List.Item
          icon={
            <ThemeIcon color="blue" size={24} radius="xl">
              <IconCircleDashed style={{ width: rem(16), height: rem(16) }} />
            </ThemeIcon>
          }
        >
          Password Manager (Coming Soon)
        </List.Item>
      </List>
    </>
  );
};

export default Aside;
