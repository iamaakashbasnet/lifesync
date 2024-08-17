import axios from 'axios';
import { toast } from 'react-toastify';

interface NoteDataState {
  title: string;
  content: string;
}

interface NoteListState {
  id: number;
  title: string;
  content: string;
}

export const addNote = async (data: NoteDataState) => {
  try {
    const res = await axios.post<NoteDataState>(
      '/backend/api/v1/note/addNote',
      data
    );
    toast.success('Note added.');
    return res.data;
  } catch (err) {
    if (axios.isAxiosError(err) && err.response) {
      toast.error('Something went wrong!');
    }
  }
};

export const listNote = async () => {
  try {
    const res = await axios.get<NoteListState[]>(
      '/backend/api/v1/note/getAllNotes'
    );
    return res.data;
  } catch (err) {
    if (axios.isAxiosError(err) && err.response) {
      toast.error('Something went wrong!');
    }
  }
};

export const getNoteById = async (id: number) => {
  try {
    const res = await axios.get<NoteDataState>(
      `/backend/api/v1/note/getNote/${id}`
    );
    return res.data;
  } catch (err) {
    if (axios.isAxiosError(err) && err.response) {
      toast.error('Something went wrong!');
    }
  }
};
