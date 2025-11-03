import React, { useState } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";

interface UpdateNotesProps {
  note?: {
    id: number;
    topic: string;
    description?: string;
    priority?: string;
  } | null;
  onClose: () => void;
  onSave: (note: any) => void;
}

export default function UpdateNotes({
  note,
  onClose,
  onSave,
}: UpdateNotesProps) {
  const [topic, setTopic] = useState(note?.topic || "");
  const [description, setDescription] = useState(note?.description || "");
  const [priority, setPriority] = useState(note?.priority || "Medium");

  const handleSave = () => {
    const updatedNote = {
      ...note,
      topic,
      description,
      priority,
      id: note?.id || Date.now(),
    };
    onSave(updatedNote);
  };

  return (
    <div className="fixed mt-20 inset-0 z-50 flex items-center justify-center bg-black/40 backdrop-blur-sm">
      <div className="relative w-[600px] rounded-2xl border border-gray-200 bg-white p-6 shadow-2xl dark:border-white/10 dark:bg-gray-900">
        <div className="flex items-start justify-between border-b border-gray-200 pb-3 dark:border-white/10">
          <div>
            <h2 className="text-xl font-semibold text-gray-800 dark:text-white">
              {note ? "Update Note" : "Add New Note"}
            </h2>
            <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
              {note
                ? "Edit and update your note below."
                : "Fill in details to add a new note."}
            </p>
          </div>
          <button
            onClick={onClose}
            className="rounded-full p-2 hover:bg-gray-100 dark:hover:bg-gray-800"
            title="Close"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="h-5 w-5 text-gray-600 dark:text-gray-300"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M6 18L18 6M6 6l12 12"
              />
            </svg>
          </button>
        </div>

        {/* Form Section */}
        <div className="mt-5 space-y-5">
          {/* Title */}
          <div>
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Title
            </label>
            <input
              type="text"
              value={topic}
              onChange={(e) => setTopic(e.target.value)}
              placeholder="Enter note title..."
              className="w-full rounded-lg border border-gray-300 bg-gray-50 px-3 py-2 text-sm text-gray-800 focus:border-blue-500 focus:outline-none dark:border-gray-700 dark:bg-gray-800 dark:text-white dark:focus:border-blue-500"
            />
          </div>

          {/* Description */}
          <div>
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Description
            </label>
            <div className="rounded-lg border border-gray-300 dark:border-gray-700 overflow-hidden">
              <ReactQuill
                theme="snow"
                value={description}
                onChange={setDescription}
                className="h-[150px] dark:bg-gray-800"
              />
            </div>
          </div>

          {/* Priority */}
          <div>
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Priority
            </label>
            <select
              value={priority}
              onChange={(e) => setPriority(e.target.value)}
              className="w-full rounded-lg border border-gray-300 bg-gray-50 px-3 py-2 text-sm text-gray-800 focus:border-blue-500 focus:outline-none dark:border-gray-700 dark:bg-gray-800 dark:text-white dark:focus:border-blue-500"
            >
              <option>High</option>
              <option>Medium</option>
              <option>Low</option>
            </select>
          </div>
        </div>

        {/* Footer */}
        <div className="mt-6 flex justify-end gap-3 border-t border-gray-200 pt-4 dark:border-white/10">
          <button
            onClick={onClose}
            className="rounded-lg border border-gray-300 px-4 py-1.5 text-sm font-medium text-gray-700 hover:bg-gray-100 dark:border-gray-700 dark:text-gray-300 dark:hover:bg-gray-800"
          >
            Cancel
          </button>
          <button
            onClick={handleSave}
            className="rounded-lg bg-blue-600 px-4 py-1.5 text-sm font-medium text-white hover:bg-blue-700"
          >
            {note ? "Update Note" : "Add Note"}
          </button>
        </div>
      </div>
    </div>
  );
}
