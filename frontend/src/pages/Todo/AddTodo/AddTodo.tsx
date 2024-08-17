import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { TextInput, Textarea, Button, Stack, Group } from '@mantine/core';
import { useMutation } from 'react-query';

import { addTodo } from './../api';

const AddTodo = () => {
  const navigate = useNavigate();
  const [todoData, setTodoData] = useState({ title: '', description: '' });

  const { mutateAsync, isLoading } = useMutation({
    mutationFn: () => addTodo(todoData),
    onSuccess: async () => {
      navigate('/dashboard/view-todo');
    },
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setTodoData((prevData) => ({
      ...prevData,
      [e.target.name]: e.target.value,
    }));
  };

  const handleAddTodo = async () => {
    await mutateAsync();
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
          <Button onClick={handleAddTodo}>
            {isLoading ? 'Adding...' : 'Add Todo'}
          </Button>
        </Group>
      </Stack>
    </>
  );
};

export default AddTodo;
