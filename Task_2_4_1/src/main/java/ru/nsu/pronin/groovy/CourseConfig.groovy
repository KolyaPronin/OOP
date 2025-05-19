import java.time.LocalDate
import ru.nsu.pronin.config.Task
import ru.nsu.pronin.config.Student
import ru.nsu.pronin.config.Checkpoint

def course = binding.getVariable("course")

course.course {
    // === TASKS ===
    task(id: 'Task_1_1_1', name: 'Pyramid Sort', maxScore: 1, softDeadline: '2024-09-30', hardDeadline: '2024-09-30')
    task(id: 'Task_1_1_2', name: 'Console Blackjack', maxScore: 2, softDeadline: '2024-10-07', hardDeadline: '2024-10-14')
    task(id: 'Task_1_1_3', name: 'Equation Operations', maxScore: 2, softDeadline: '2024-10-14', hardDeadline: '2024-10-21')
    task(id: 'Task_1_2_1', name: 'Graph', maxScore: 2, softDeadline: '2024-10-21', hardDeadline: '2024-10-28')
    task(id: 'Task_1_2_2', name: 'Hash Table', maxScore: 2, softDeadline: '2024-10-28', hardDeadline: '2024-11-11')
    task(id: 'Task_1_3_1', name: 'Substring Search', maxScore: 2, softDeadline: '2024-11-11', hardDeadline: '2024-11-18')
    task(id: 'Task_1_4_1', name: 'Grade Book', maxScore: 2, softDeadline: '2024-11-25', hardDeadline: '2024-11-25')
    task(id: 'Task_1_5_1', name: 'Markdown Generator', maxScore: 2, softDeadline: '2024-12-16', hardDeadline: '2024-12-16')

    // === GROUP AND STUDENT ===
    group(name: '22105') {
        student(username: 'KIRILL', fullName: 'Kirill Kozlov', repo: 'https://github.com/kozlovwv/OOP.git')
    }

    // === TASK ASSIGNMENTS ===
    assignments {
        assign('KIRILL', [
            'Task_1_1_1',
            'Task_1_1_2',
            'Task_1_1_3',
            'Task_1_2_1',
            'Task_1_2_2',
            'Task_1_3_1',
            'Task_1_4_1',
            'Task_1_5_1'
        ])
    }

    // === CHECKPOINTS ===
    checkpoint(name: 'CP1', date: '2024-10-15')
    checkpoint(name: 'CP2', date: '2024-11-15')
    checkpoint(name: 'Final', date: '2024-12-20')
}
