import React, { useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHeader,
  TableRow,
} from "../../ui/table";

import { Pencil, Trash2, Plus } from "lucide-react";
import Button from "../../ui/button/Button";
import UpdateNotes from "../../modal/NotesModal";

interface Note {
  id: number;
  topic: string;
  description?: string;
  priority?: string;
  dateCreated: string;
}

const initialNotes: Note[] = [
  {
    id: 1,
    topic: "React Hooks Deep Dive",
    description: "Learn about useEffect, useMemo, and custom hooks.",
    priority: "High",
    dateCreated: "Nov 01, 2025",
  },
  {
    id: 2,
    topic: "TypeScript Utility Types",
    description: "Explore Partial, Pick, and Record utilities.",
    priority: "Medium",
    dateCreated: "Nov 02, 2025",
  },
];

export default function NotesTable() {
  const [notes, setNotes] = useState<Note[]>(initialNotes);
  const [showModal, setShowModal] = useState(false);
  const [selectedNote, setSelectedNote] = useState<Note | null>(null);

  const handleAdd = () => {
    setSelectedNote(null);
    setShowModal(true);
  };

  const handleEdit = (note: Note) => {
    setSelectedNote(note);
    setShowModal(true);
  };

  const handleDelete = (id: number) => {
    setNotes(notes.filter((note) => note.id !== id));
  };

  const handleSave = (updatedNote: Note) => {
    if (selectedNote) {
      setNotes(notes.map((n) => (n.id === updatedNote.id ? updatedNote : n)));
    } else {
      setNotes([
        ...notes,
        { ...updatedNote, id: Date.now(), dateCreated: "Nov 03, 2025" },
      ]);
    }
    setShowModal(false);
  };

  return (
    <>
      <div className="overflow-hidden rounded-xl border border-gray-200 bg-white dark:border-white/[0.05] dark:bg-white/[0.03]">
        <div className="flex items-center justify-between px-6 py-4 border-b border-gray-100 dark:border-white/[0.05]">
          <h2 className="text-lg font-semibold text-gray-800 dark:text-white/90">
            Notes
          </h2>
          <Button
            className="flex items-center gap-2 rounded-lg bg-blue-600 px-4 py-2 text-sm font-medium text-white hover:bg-blue-700"
            onClick={handleAdd}
          >
            <Plus size={16} />
            Add Note
          </Button>
        </div>

        <div className="max-w-full overflow-x-auto">
          <div className="min-w-[700px]">
            <Table>
              <TableHeader className="border-b border-gray-100 dark:border-white/[0.05]">
                <TableRow>
                  <TableCell
                    isHeader
                    className="px-6 py-3 font-medium text-gray-500 text-start text-theme-xs dark:text-gray-400"
                  >
                    Topic
                  </TableCell>
                  <TableCell
                    isHeader
                    className="px-6 py-3 font-medium text-gray-500 text-start text-theme-xs dark:text-gray-400"
                  >
                    Date Created
                  </TableCell>
                  <TableCell
                    isHeader
                    className="px-6 py-3 font-medium text-gray-500 text-start text-theme-xs dark:text-gray-400"
                  >
                    Actions
                  </TableCell>
                </TableRow>
              </TableHeader>

              <TableBody className="divide-y divide-gray-100 dark:divide-white/[0.05]">
                {notes.length > 0 ? (
                  notes.map((note) => (
                    <TableRow key={note.id}>
                      <TableCell className="px-6 py-4 text-start font-medium text-gray-800 dark:text-white/90">
                        {note.topic}
                      </TableCell>

                      <TableCell className="px-6 py-4 text-gray-500 dark:text-gray-400">
                        {note.dateCreated}
                      </TableCell>

                      <TableCell className="px-6 py-4 text-start flex items-center gap-3">
                        <button
                          onClick={() => handleEdit(note)}
                          className="flex items-center gap-1 rounded-md border border-gray-200 bg-white px-3 py-1 text-sm text-gray-700 hover:bg-gray-100 dark:border-white/[0.1] dark:bg-white/[0.05] dark:text-gray-300"
                        >
                          <Pencil size={14} />
                          Update
                        </button>

                        <button
                          onClick={() => handleDelete(note.id)}
                          className="flex items-center gap-1 rounded-md border border-red-200 bg-red-50 px-3 py-1 text-sm text-red-600 hover:bg-red-100 dark:border-red-500/30 dark:bg-red-500/10 dark:text-red-400"
                        >
                          <Trash2 size={14} />
                          Delete
                        </button>
                      </TableCell>
                    </TableRow>
                  ))
                ) : (
                  <TableRow>
                    <TableCell className="py-8">
                      <div className="w-full text-center text-gray-500 dark:text-gray-400">
                        No notes available.
                      </div>
                    </TableCell>
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </div>
        </div>
      </div>

      {showModal && (
        <UpdateNotes 
        
          note={selectedNote}
          onClose={() => setShowModal(false)}
          onSave={handleSave}
        />
      )}
    </>
  );
}
