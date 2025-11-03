import {
  Table,
  TableBody,
  TableCell,
  TableHeader,
  TableRow,
} from "../../ui/table";

import Badge from "../../ui/badge/Badge";
import { Edit, Trash2 } from "lucide-react";
// import {}

interface Task {
  id: number;
  taskName: string;
  description: string;
  deadline: string;
  status: "Active" | "Pending" | "Completed" | "Overdue";
  priority: "High" | "Medium" | "Low";
}

const taskData: Task[] = [
  {
    id: 1,
    taskName: "Design Homepage",
    description: "Create responsive UI for homepage layout",
    deadline: "Nov 05, 2025",
    status: "Active",
    priority: "High",
  },
  {
    id: 2,
    taskName: "API Integration",
    description: "Integrate backend APIs for user module",
    deadline: "Nov 10, 2025",
    status: "Pending",
    priority: "Medium",
  },
  {
    id: 3,
    taskName: "Write Blog Content",
    description: "Write SEO-friendly articles for blog section",
    deadline: "Nov 03, 2025",
    status: "Completed",
    priority: "Low",
  },
  {
    id: 4,
    taskName: "Social Media Campaign",
    description: "Plan and execute marketing strategy",
    deadline: "Nov 12, 2025",
    status: "Overdue",
    priority: "High",
  },
  {
    id: 5,
    taskName: "Frontend Optimization",
    description: "Improve site performance and loading speed",
    deadline: "Nov 07, 2025",
    status: "Pending",
    priority: "Medium",
  },
];

export default function PendingTaskTable() {
  const pendingTasks = taskData.filter((task) => task.status === "Pending");

  return (
    <div className="overflow-hidden rounded-xl border border-gray-200 bg-white dark:border-white/[0.05] dark:bg-white/[0.03]">
      <div className="max-w-full overflow-x-auto">
        <div className="min-w-[1000px]">
          <Table>
            <TableHeader className="border-b border-gray-100 dark:border-white/[0.05]">
              <TableRow>
                <TableCell
                  isHeader
                  className="px-6 py-3 font-medium text-gray-500 text-start text-theme-xs dark:text-gray-400"
                >
                  Task
                </TableCell>
                <TableCell
                  isHeader
                  className="px-6 py-3 font-medium text-gray-500 text-start text-theme-xs dark:text-gray-400"
                >
                  Description
                </TableCell>
                <TableCell
                  isHeader
                  className="px-6 py-3 font-medium text-gray-500 text-start text-theme-xs dark:text-gray-400"
                >
                  Deadline
                </TableCell>
                <TableCell
                  isHeader
                  className="px-6 py-3 font-medium text-gray-500 text-start text-theme-xs dark:text-gray-400"
                >
                  Status
                </TableCell>
                <TableCell
                  isHeader
                  className="px-6 py-3 font-medium text-gray-500 text-start text-theme-xs dark:text-gray-400"
                >
                  Priority
                </TableCell>
                <TableCell
                  isHeader
                  className="px-6 py-3 font-medium text-gray-500 text-center text-theme-xs dark:text-gray-400"
                >
                  Actions
                </TableCell>
              </TableRow>
            </TableHeader>

            {/* ---------- Table Body ---------- */}
            <TableBody className="divide-y divide-gray-100 dark:divide-white/[0.05]">
              {pendingTasks.length > 0 ? (
                pendingTasks.map((task) => (
                  <TableRow key={task.id}>
                    <TableCell className="px-6 py-4 text-start">
                      <span className="font-medium text-gray-800 text-theme-sm dark:text-white/90">
                        {task.taskName}
                      </span>
                    </TableCell>

                    <TableCell className="px-6 py-3 text-gray-500 text-theme-sm dark:text-gray-400">
                      {task.description}
                    </TableCell>

                    <TableCell className="px-6 py-3 text-gray-500 text-theme-sm dark:text-gray-400">
                      {task.deadline}
                    </TableCell>

                    <TableCell className="px-6 py-3 text-start">
                      <Badge size="sm" color="warning">
                        {task.status}
                      </Badge>
                    </TableCell>

                    <TableCell className="px-6 py-3 text-start">
                      <Badge
                        size="sm"
                        color={
                          task.priority === "High"
                            ? "error"
                            : task.priority === "Medium"
                            ? "warning"
                            : "success"
                        }
                      >
                        {task.priority}
                      </Badge>
                    </TableCell>

                    {/* ---------- Actions ---------- */}
                    <TableCell className="px-6 py-3 text-center">
                      <div className="flex justify-center gap-3">
                        <button
                          className="p-1.5 rounded-md border border-gray-200 hover:bg-gray-100 dark:border-white/[0.1] dark:hover:bg-white/[0.05] transition"
                          title="Edit Task"
                        >
                          <Edit className="w-4 h-4 text-blue-500" />
                        </button>
                        <button
                          className="p-1.5 rounded-md border border-gray-200 hover:bg-gray-100 dark:border-white/[0.1] dark:hover:bg-white/[0.05] transition"
                          title="Delete Task"
                        >
                          <Trash2 className="w-4 h-4 text-red-500" />
                        </button>
                      </div>
                    </TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell className="py-6 text-center text-gray-500 dark:text-gray-400 w-full">
                    <div className="text-center">No pending tasks found.</div>
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </div>
      </div>
    </div>
  );
}
