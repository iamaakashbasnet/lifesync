import { Card, Text, Stack } from '@mantine/core';

const ListNote = () => {
  const notes = [
    { id: 1, title: 'Note 1' },
    { id: 2, title: 'Note 2' },
    { id: 3, title: 'Note 3' },
  ];

  return (
    <div>
      <h2>My Notes</h2>

      <Stack gap="md">
        {notes.map((note) => (
          <Card shadow="sm" padding="lg" key={note.id}>
            <Text fw={500}>{note.title}</Text>
          </Card>
        ))}
      </Stack>
    </div>
  );
};

export default ListNote;
