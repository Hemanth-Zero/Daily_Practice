//3408 Design TaskManager
//got some 352ms beating 36.5%
class TaskManager {
    class Task {
        int userId;
        int taskId;
        int priority;

        Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }
    }

    private java.util.Map<Integer, Task> taskMap = new java.util.HashMap<>();
    private java.util.TreeSet<Task> taskSet = new java.util.TreeSet<>((a, b) -> {
        if (a.priority != b.priority) return b.priority - a.priority;
        return b.taskId - a.taskId;
    });

    public TaskManager(java.util.List<java.util.List<Integer>> tasks) {
        for (java.util.List<Integer> t : tasks) {
            add(t.get(0), t.get(1), t.get(2));
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        taskMap.put(taskId, task);
        taskSet.add(task);
    }
    
    public void edit(int taskId, int newPriority) {
        Task task = taskMap.get(taskId);
        if (task != null) {
            taskSet.remove(task);
            task.priority = newPriority;
            taskSet.add(task);
        }
    }
    
    public void rmv(int taskId) {
        Task task = taskMap.get(taskId);
        if (task != null) {
            taskSet.remove(task);
            taskMap.remove(taskId);
        }
    }
    
    public int execTop() {
        if (taskSet.isEmpty()) return -1;
        Task top = taskSet.first();
        rmv(top.taskId);
        return top.userId;
    }
}
