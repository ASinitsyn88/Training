package ru.myapp.algorithms.breadth_search;

import java.util.*;

/**
 * Поиск в ширину
 * Есть граф, каждый узел которого представлен именем продавца
 * Каждый узел этого графа может иметь соседние узлы, а те, в свою очередь, могут иметь свои соседние узлы и т.д.
 * Данный поиск умеет находить узел, соответствующий определённому условию (Например: имя продавца манго должно заканчиваться на m)
 * При этом данный поиск среди всех узлов находит ближайший узел, соответствующий указанному условию
 * Поиск работает по следующему принципу:
 * 1. Создаётся очередь (FIFO)
 * 2. В очередь добавляются элементы первого уровня (непосредственные соседи для переданного на вход элемента)
 * 3. В цикле, пока очередь не пустая, один за одним извлекаем из очереди элементы и проверяем каждый из них на принадлежность к продавцу манго (заканчивается на m)
 * 4. Если это продавец манго - завершаем работу (цель достигнута)
 * 5. Если это не продавец манго - то определяем для этого продавца соседние узлы (2 уровня) и добавляем их в конец очереди
 * 6. Обработанных продавцов складываем в список обработанных чтобы исключить бесконечные циклы и двойные проверки
 * 7. Выполняем пункты 4,5,6 до тех пор пока не найдём ближайшего продавца манго (под ближайшим подразумевается уровень)
 */
public class BreadthFirstSearch {

    private static Map<String, List<String>> graph = new HashMap<>();

    private static boolean search(String name) {
        Queue<String> searchQueue = new ArrayDeque<>(graph.get(name));
        // This list is how you keep track of which people you've searched before.
        List<String> searched = new ArrayList<>();

        while (!searchQueue.isEmpty()) {
            String person = searchQueue.poll();
            // Only search this person if you haven't already searched them
            if (!searched.contains(person)) {
                if (person_is_seller(person)) {
                    System.out.println(person + " is a mango seller!");
                    return true;
                } else {
                    searchQueue.addAll(graph.get(person));
                    // Marks this person as searched
                    searched.add(person);
                }
            }
        }

        return false;
    }

    private static boolean person_is_seller(String name) {
        return name.endsWith("m");
    }

    public static void main(String[] args) {
        graph.put("you", Arrays.asList("alice", "bob", "claire"));
        graph.put("bob", Arrays.asList("anuj", "peggy"));
        graph.put("alice", Arrays.asList("peggy"));
        graph.put("claire", Arrays.asList("thom", "jonny"));
        graph.put("anuj", Collections.emptyList());
        graph.put("peggy", Collections.emptyList());
        graph.put("thom", Collections.emptyList());
        graph.put("jonny", Collections.emptyList());

        search("you");
    }
}