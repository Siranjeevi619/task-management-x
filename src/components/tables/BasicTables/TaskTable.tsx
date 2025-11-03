import {
  Table,
  TableBody,
  TableCell,
  TableHeader,
  TableRow,
} from "../../ui/table";

import Badge from "../../ui/badge/Badge";

interface Task {
  id: number;
  taskName: string;
  description: string;
  assignees: string[];
  deadline: string;
  status: "Active" | "Pending" | "Completed" | "Overdue";
  priority: "High" | "Medium" | "Low";
}

const taskData: Task[] = [
  {
    id: 1,
    taskName: "Design Homepage",
    description: "Create responsive UI for homepage layout",
    assignees: [
      "./images/user/user-22.jpg",
      "./images/user/user-23.jpg",
      "./images/user/user-24.jpg",
    ],
    deadline: "Nov 05, 2025",
    status: "Active",
    priority: "High",
  },
  {
    id: 2,
    taskName: "API Integration",
    description: "Integrate backend APIs for user module",
    assignees: ["./images/user/user-25.jpg", "./images/user/user-26.jpg"],
    deadline: "Nov 10, 2025",
    status: "Pending",
    priority: "Medium",
  },
  {
    id: 3,
    taskName: "Write Blog Content",
    description: "Write SEO-friendly articles for blog section",
    assignees: ["./images/user/user-27.jpg"],
    deadline: "Nov 03, 2025",
    status: "Completed",
    priority: "Low",
  },
  {
    id: 4,
    taskName: "Social Media Campaign",
    description: "Plan and execute marketing strategy",
    assignees: [
      "./images/user/user-28.jpg",
      "./images/user/user-29.jpg",
      "./images/user/user-30.jpg",
    ],
    deadline: "Nov 12, 2025",
    status: "Overdue",
    priority: "High",
  },
  {
    id: 5,
    taskName: "Frontend Optimization",
    description: "Improve site performance and loading speed",
    assignees: [
      "./images/user/user-31.jpg",
      "./images/user/user-32.jpg",
      "./images/user/user-33.jpg",
    ],
    deadline: "Nov 07, 2025",
    status: "Active",
    priority: "Medium",
  },
];

export default function TaskTable() {
  return (
    <div className="overflow-hidden rounded-xl border border-gray-200 bg-white dark:border-white/[0.05] dark:bg-white/[0.03]">
      <div className="max-w-full overflow-x-auto">
        <div className="min-w-[1102px]">
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
                {/* <TableCell
                  isHeader
                  className="px-6 py-3 font-medium text-gray-500 text-start text-theme-xs dark:text-gray-400"
                >
                  Assignee(s)
                </TableCell> */}
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
              </TableRow>
            </TableHeader>

            <TableBody className="divide-y divide-gray-100 dark:divide-white/[0.05]">
              {taskData.map((task) => (
                <TableRow key={task.id}>
                  <TableCell className="px-6 py-4 text-start">
                    <span className="font-medium text-gray-800 text-theme-sm dark:text-white/90">
                      {task.taskName}
                    </span>
                  </TableCell>

                  <TableCell className="px-6 py-3 text-gray-500 text-theme-sm dark:text-gray-400">
                    {task.description}
                  </TableCell>
                  {/* 
                  <TableCell className="px-6 py-3 text-gray-500 text-start text-theme-sm dark:text-gray-400">
                    <div className="flex -space-x-2">
                      {task.assignees.map((img, index) => (
                        <div
                          key={index}
                          className="w-6 h-6 overflow-hidden border-2 border-white rounded-full dark:border-gray-900"
                        >
                          <img
                            width={24}
                            height={24}
                            src={img}
                            alt={`Assignee ${index + 1}`}
                            className="w-full h-full object-cover"
                          />
                        </div>
                      ))}
                    </div>
                  </TableCell> */}

                  <TableCell className="px-6 py-3 text-gray-500 text-theme-sm dark:text-gray-400">
                    {task.deadline}
                  </TableCell>

                  <TableCell className="px-6 py-3 text-start">
                    <Badge
                      size="sm"
                      color={
                        task.status === "Active"
                          ? "success"
                          : task.status === "Pending"
                          ? "warning"
                          : task.status === "Completed"
                          ? "info"
                          : "error"
                      }
                    >
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
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
      </div>
    </div>
  );
}
