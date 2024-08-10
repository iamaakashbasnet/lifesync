import { useState } from 'react';
import { TextInput, Textarea, Button, Stack, Group } from '@mantine/core';

const AddTodo = () => {
  const [todoData, setTodoData] = useState({ title: '', description: '' });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setTodoData((prevData) => ({
      ...prevData,
      [e.target.name]: e.target.value,
    }));
  };

  const handleAddTodo = () => {
    console.log(todoData);
  };

  return (
    <>
      <h2>Add Todo</h2>
      <Stack gap="md">
        <TextInput
          label="Title"
          placeholder="Enter todo title"
          value={todoData.title}
          name="title"
          onChange={handleChange}
          required
        />

        <Textarea
          label="Description"
          placeholder="Enter todo description"
          value={todoData.description}
          onChange={handleChange}
          autosize
          name="description"
          minRows={3}
          required
        />

        <Group mt="md">
          <Button onClick={handleAddTodo}>Add Todo</Button>
        </Group>
      </Stack>
    </>
  );
};

export default AddTodo;
