import { RichTextEditor, Link } from '@mantine/tiptap';
import { useEditor } from '@tiptap/react';
import Highlight from '@tiptap/extension-highlight';
import StarterKit from '@tiptap/starter-kit';
import Underline from '@tiptap/extension-underline';
import TextAlign from '@tiptap/extension-text-align';
import Superscript from '@tiptap/extension-superscript';
import SubScript from '@tiptap/extension-subscript';
import { Button, Stack, TextInput } from '@mantine/core';
import { useState, useEffect } from 'react';
import { useMutation } from 'react-query';

import { addNote } from './../api';
import { useNavigate } from 'react-router-dom';

const AddNote = () => {
  const navigate = useNavigate();
  const editor = useEditor({
    extensions: [
      StarterKit,
      Underline,
      Link,
      Superscript,
      SubScript,
      Highlight,
      TextAlign.configure({ types: ['heading', 'paragraph'] }),
    ],
    content: '',
  });

  const [noteData, setNoteData] = useState({
    title: '',
    content: '',
  });

  useEffect(() => {
    if (editor) {
      const handleEditorUpdate = () => {
        setNoteData((prevData) => ({
          ...prevData,
          content: editor.getHTML(),
        }));
      };

      editor.on('update', handleEditorUpdate);

      return () => {
        editor.off('update', handleEditorUpdate);
      };
    }
  }, [editor]);

  const { mutateAsync, isLoading } = useMutation({
    mutationFn: () => addNote(noteData),
    onSuccess: async () => {
      navigate('/dashboard/list-note');
    },
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setNoteData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleAddNote = async () => {
    await mutateAsync();
  };

  return (
    <>
      <h2>Add Note</h2>
      <Stack gap={5}>
        <TextInput
          label="Note Title"
          placeholder="Enter note title"
          name="title"
          value={noteData.title}
          onChange={handleChange}
          required
        />

        <RichTextEditor editor={editor} style={{ minHeight: 500 }}>
          <RichTextEditor.Toolbar sticky stickyOffset={60}>
            <RichTextEditor.ControlsGroup>
              <RichTextEditor.Bold />
              <RichTextEditor.Italic />
              <RichTextEditor.Underline />
              <RichTextEditor.Strikethrough />
              <RichTextEditor.ClearFormatting />
              <RichTextEditor.Highlight />
              <RichTextEditor.Code />
            </RichTextEditor.ControlsGroup>

            <RichTextEditor.ControlsGroup>
              <RichTextEditor.H1 />
              <RichTextEditor.H2 />
              <RichTextEditor.H3 />
              <RichTextEditor.H4 />
            </RichTextEditor.ControlsGroup>

            <RichTextEditor.ControlsGroup>
              <RichTextEditor.Blockquote />
              <RichTextEditor.Hr />
              <RichTextEditor.BulletList />
              <RichTextEditor.OrderedList />
              <RichTextEditor.Subscript />
              <RichTextEditor.Superscript />
            </RichTextEditor.ControlsGroup>

            <RichTextEditor.ControlsGroup>
              <RichTextEditor.Link />
              <RichTextEditor.Unlink />
            </RichTextEditor.ControlsGroup>

            <RichTextEditor.ControlsGroup>
              <RichTextEditor.AlignLeft />
              <RichTextEditor.AlignCenter />
              <RichTextEditor.AlignJustify />
              <RichTextEditor.AlignRight />
            </RichTextEditor.ControlsGroup>

            <RichTextEditor.ControlsGroup>
              <RichTextEditor.Undo />
              <RichTextEditor.Redo />
            </RichTextEditor.ControlsGroup>
          </RichTextEditor.Toolbar>

          <RichTextEditor.Content content={noteData.content} />
        </RichTextEditor>

        <Button type="button" onClick={handleAddNote}>
          {isLoading ? 'Loading...' : 'Add note'}
        </Button>
      </Stack>
    </>
  );
};

export default AddNote;
