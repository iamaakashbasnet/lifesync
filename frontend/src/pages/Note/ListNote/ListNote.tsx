import { Card, Text, Stack } from '@mantine/core';
import { useQuery } from 'react-query';
import { listNote } from './../api';
import { Link } from 'react-router-dom';

const ListNote = () => {
  const { data, isLoading } = useQuery({
    queryFn: () => listNote(),
    queryKey: ['fetch-notes'],
  });

  return (
    <div>
      <h2>My Notes</h2>

      <Stack gap="md">
        {isLoading ? (
          <p>Loading notes...</p>
        ) : (
          data &&
          data.map((note) => (
            <Link
              key={note.id}
              to={`/dashboard/view-note/${note.id}`}
              style={{ textDecoration: 'none' }}
            >
              <Card shadow="sm" padding="lg" key={note.id}>
                <Text fw={500}>{note.title}</Text>
              </Card>
            </Link>
          ))
        )}
      </Stack>
    </div>
  );
};

export default ListNote;
