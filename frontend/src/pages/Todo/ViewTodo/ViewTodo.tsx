import { Card, Text, Badge, Group, Stack } from '@mantine/core';

const ViewTodo = () => {
  const todos = [
    {
      id: 1,
      title: 'Title 1',
      description: 'lorem ipsum dolor sit amet',
      createdDate: '2021',
      updatedDate: '2021',
      isCompleted: false,
    },
    {
      id: 2,
      title: 'Title 2',
      description: 'lorem ipsum dolor sit amet',
      createdDate: '2021',
      updatedDate: '2022',
      isCompleted: true,
    },
    {
      id: 3,
      title: 'Title 3',
      description: 'lorem ipsum dolor',
      createdDate: '2045',
      updatedDate: '5042',
      isCompleted: false,
    },
  ];

  return (
    <>
      <h2>My todos</h2>
      <Stack gap="md">
        {todos.map((todo) => (
          <Card shadow="sm" padding="lg" key={todo.id}>
            <Group gap={20}>
              <Text fw={500}>{todo.title}</Text>
              <Badge color={todo.isCompleted ? 'green' : 'red'}>
                {todo.isCompleted ? 'Completed' : 'Pending'}
              </Badge>
            </Group>

            <Text size="sm" color="dimmed" mt="xs">
              {todo.description}
            </Text>

            <Group mt="md" gap="xs">
              <Text size="xs" color="dimmed">
                Created: {todo.createdDate}
              </Text>
              <Text size="xs" color="dimmed">
                Updated: {todo.updatedDate}
              </Text>
            </Group>
          </Card>
        ))}
      </Stack>
    </>
  );
};

export default ViewTodo;
