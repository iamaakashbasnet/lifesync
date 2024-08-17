import { Card, Text, Badge, Group, Stack } from '@mantine/core';
import { viewTodo } from './../api';
import { useQuery } from 'react-query';

const ViewTodo = () => {
  const { data, isLoading } = useQuery({
    queryFn: () => viewTodo(),
    queryKey: ['fetch-todos'],
  });

  return (
    <>
      <h2>My todos</h2>
      {isLoading ? (
        <p>Loading todos...</p>
      ) : (
        <Stack gap="md">
          {data &&
            data.map((todo) => (
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
                    Created: {new Date(todo.createdDate).toLocaleDateString()}
                  </Text>
                  <Text size="xs" color="dimmed">
                    Updated: {new Date(todo.updatedDate).toLocaleDateString()}
                  </Text>
                </Group>
              </Card>
            ))}
        </Stack>
      )}
    </>
  );
};

export default ViewTodo;
