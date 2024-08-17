import axios from 'axios';
import { toast } from 'react-toastify';

interface AddTodoState {
  title: string;
  description: string;
}

interface ViewTodoState {
  id: number;
  title: string;
  description: string;
  createdDate: string;
  updatedDate: string;
  isCompleted: boolean;
}

export const addTodo = async (data: AddTodoState) => {
  try {
    const res = await axios.post<AddTodoState>(
      '/backend/api/v1/todo/addTodo',
      data
    );
    toast.success('Todo added.');
    return res.data;
  } catch (err) {
    if (axios.isAxiosError(err) && err.response) {
      toast.error('Something went wrong!');
    }
  }
};

export const viewTodo = async () => {
  try {
    const res = await axios.get<ViewTodoState[]>('/backend/api/v1/todo/user');
    return res.data;
  } catch (err) {
    if (axios.isAxiosError(err) && err.response) {
      toast.error('Something went wrong!');
    }
  }
};
