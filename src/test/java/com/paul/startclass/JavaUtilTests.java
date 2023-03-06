package com.paul.startclass;

import net.bytebuddy.TypeCache;
import net.bytebuddy.utility.QueueFactory;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootTest
public class JavaUtilTests {

    //Interface Iterable - предоставляет методы для обхода массива через foreach, iterator
    @Test
    void iterableInterface() {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");

        Iterable<String> iterable = arrayList;

        // Использует методы для обхода коллекций в цикле, через forEach
        iterable.forEach(System.out::println); // Вариант 1
        // Вариант 2
        iterable.forEach((element) -> {
            System.out.println(element);
        });

        // Через for
        for (Object o: iterable) {
            System.out.println(o);
        }

        //Через itterator
        Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //Interface Iterator - необходим для обхода массива, получение следующего элемента, или определение есть ли следующий
    //Interface Iterator - requiered for collection traversing, getting a next element, checking if there is a next element, deleting a curent element
    @Test
    void iteratorInterface() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");

        Iterator<String> iterator = arrayList.iterator();

        //Проверяем есть ли следующий элемент(Checking, if there is the next element )
        boolean hasNext = iterator.hasNext(); // true

        //Мы получаем следующий элемент(Getting the next element)
        String str = iterator.next(); // 1

        iterator.next();

        // Удаляет текущий элемент, в данный момент 2ой, также и в самой коллекции ArrayList(Deleting the current element)
        iterator.remove(); // iterator = {"1", "3"}

        // Обход элементов массива более лаконичным способом(more convenient array traversing)
        arrayList.iterator().forEachRemaining((element) -> {
            System.out.println(element);
        });

        System.out.println();

    }


    // Collection interface - базовый интерфейс для всех коллекций и других интерфейсов коллекций, который включает такие методы как добавить(add(), addALL()), удалить(remove(), removeAll, removeIf(), retainAll()), проверить на наличие элементов(containsAll(),contains())
    @Test
    void collectionInterfaceTest(){
        // Создаем коллекцию из значений
        Collection<String> collection = new ArrayList<>(Arrays.asList("1","2","3"));

        // Добавление элемента(Adding an element)
        collection.add("4");

        // Добавление нескольких элементов(Adding multiple elements)
        collection.addAll(List.of("5", "6", "7"));

        // Получение количество элементов(Getting the numbers of elements)
        Collection<String> collection1 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        int size = collection1.size(); // 5

        // Определяем есть ли данный элемент(determining whether there is a given element)
        Collection<String> collection2 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        boolean containsElement = collection2.contains("5"); // true
        boolean containsElementfalse = collection2.contains("10"); // false

        //Определяем есть ли несколько элементов в коллекции
        Collection<String> collection3 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        boolean containsAll = collection3.containsAll(List.of("1", "3", "4"));
        boolean containsAllfalse = collection3.containsAll(List.of("1", "3", "41")); // false

        //Проверяет наличие элементов(checking whether there the elements)
        Collection<String> collection4 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        boolean empty = collection4.isEmpty(); // true

        //Удаление элемента из коллекции (deleting an element from collection)
        Collection<String> collection5 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        collection5.remove("2"); // collection {"1","3","4","5"}

        //Удаление нескольких элементов из коллекции(Deleting multiple elements from a collection)
        Collection<String> collection6 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        collection6.removeAll(List.of("1","4")); // collection {"2","3","5"}

        // Удаление элементов соответствующих условиям(Deleting the elements that match the conditions )
        Collection<String> collection7 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        collection7.removeIf(elem -> elem.equals("3") || elem.equals("4"));  // collection {"1","2","5"}

        // Удаление элементов не совпадающие(Deleting the elements that don't mach the conditions)
        Collection<String> collection8 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        collection8.retainAll(List.of("1","2","3")); // collection {"1","2","3"}

        // Удаление всех элементов (Deleting all elements)
        Collection<String> collection9 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        collection9.clear(); // collection {}

    }


    // Работа с интерфейсом List
    @Test
    void listInterfaceTest(){

        List<String> list = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        String elem = list.get(1);

        // Добавить элементы со 2го элемента
        List<String> list1 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        list1.addAll(2, List.of("1","2","3")); // list {"1","2","1","2"..,"4","5"}

        //Удаление по индексу
        List<String> list2 = new ArrayList<>(Arrays.asList("1","2","1","2","3","3","4","5"));
        list2.remove(2); // list {"1","2","4","5"}

        //Поиск по значению
        List<String> list3 = new ArrayList<>(Arrays.asList("1","2","3","4","5"));
        int index = list3.indexOf("3"); // 2

        //Поиск последнего элемента с указанным значением
        List<String> list4 = new ArrayList<>(Arrays.asList("1","2","3","4","5","3"));
        int index2 = list4.lastIndexOf("3"); // 5

        // Получить iterator который имеет схожие методы с iterator но есть еще previous, nextIndex, set, add, ...
        List<String> list5 = new ArrayList<>(Arrays.asList("1","2","3","4","5","3"));
        ListIterator<String> stringListIterator = list5.listIterator();

        // Получить ListIterator
        List<String> list6 = new ArrayList<>(Arrays.asList("1","2","3","4","5","3"));
        ListIterator<String> stringListIterator1 = list6.listIterator(2);

        // Изменить все элементы
        List<String> list7 = new ArrayList<>(Arrays.asList("1","2","3","4","5","3"));
        list7.replaceAll(e -> e.toLowerCase() + "prefix");

        //Сортировка list
        List<String> list8 = new ArrayList<>(Arrays.asList("3","5","2","6","3"));
        list8.sort((e,w)-> e.compareTo(w));
        //list8.sort(String::compareTo);

        //Получить подмассив
        List<String> list9 = new ArrayList<>(Arrays.asList("1","2","3","4","5","3"));
        List<String> subList = list9.subList(2, 4);// list {"3","4"}

        //Параллельный быстрый обход элементов
        List<String> list10 = new ArrayList<>(Arrays.asList("1","2","3","4","5","3"));
        Spliterator<String> spliterator = list10.spliterator();

    }



    // Список на основе массива
    @Test
    void arrayListTest(){
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("1","2","3","4","5","3"));

        // Создание клона ArrayList
        ArrayList<String> arrayList1 = (ArrayList<String>) arrayList.clone();
        arrayList1.replaceAll(e->e+"prefix");

        LinkedList<String> linkedList= new LinkedList<>(Arrays.asList("1","2","3","4","5","3"));

        // Также можно создать ArrayList из другой коллекции
        ArrayList<String> list2 = new ArrayList<>(linkedList);

        // Для оптимизации сразу создаем массив с большим количеством элементов
        ArrayList<String> list3 = new ArrayList<>(10000);


        //----------- O(1) - Быстрые операции ArrayList:------------

        // ArrayList - O(1) - Добавление в конец
        arrayList.add("22");

        // ArrayList - O(1) - Получение размера
        int size = arrayList.size();

        // ArrayList - O(1) - Проверка массива на пустоту
        boolean empty = arrayList.isEmpty();

        // ArrayList - O(1) - Получение элемента
        String s1 = arrayList.get(1);

        // ArrayList - O(1) - Изменение элемента
        arrayList.set(3,"11");

        // ArrayList - O(1) - Получение итератора
        Iterator<String> iterator = arrayList.iterator();

        // ArrayList - O(1) - Получение listIterator
        arrayList.listIterator();

        //----------- O(n) - Долгие операции линейной сложности ArrayList:------------

        // ArrayList - O(n) - Поиск элемента по значению(O(n) - Перебор всех элементов)
        int i = arrayList.indexOf("11");

        // ArrayList - O(n) - Удаление элемента(O(n) - Перебор всех элементов для уменьшение размера и для поиска)
        arrayList.remove("11");

        // ArrayList - O(n) - Удаление элемента(O(n) - Перебор всех элементов для уменьшение размера)
        arrayList.remove(1);

        // ArrayList - O(n) - Удаление всех элементов (O(n) - Перебор всех элементов и удаление)
        arrayList.clear();

        //----------- Другие функции

        // Устанавливает минимально возможное количество элементов
        arrayList.ensureCapacity(10);

        //Параллельная работа с элементами
        arrayList.stream()
                .parallel()
                .forEach(System.out::println);

//        arrayList.

        arrayList.add("1");




    }


    // Список
    @Test
    void linkedListTest() {
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "3"));

        // Итератор в обратном порядке
        Iterator<String> iterator = linkedList.descendingIterator(); //Iterator {"3","5","4"..}

        linkedList.addFirst("11"); // Добавление в начало, выгода в том что добавляет только 1 ссылку
        linkedList.addLast("22"); //Добавляет в конец без ссылки

        String element = linkedList.element(); // Получение текущего элемента

        // Получение первого и последнего элемента
        String first = linkedList.getFirst();
        String last = linkedList.getLast();

        linkedList.offer("1"); // добавление элемента в конец при этом возвращает bool
        linkedList.offerLast("2"); // добавление элемента в конец списка при этом возвращает bool
        linkedList.offerFirst("22"); // добавление элемента в начало списка при этом возвращает bool
    }

    // Стек - первый зашел, последний вышел
    @Test
    void stackTest() {
        Stack<String> stack = new Stack<>();
        stack.addAll(Arrays.asList("1", "2", "3", "4", "5", "3"));

        stack.push("1"); // Добавить элемент вверх стека

        String pop = stack.pop(); // получить верхний элемент стека и удалить его из стека

        String peek = stack.peek(); //Вернуть верхний элемент стека но не удалять его
    }


    // Vector
    @Test
    void vectorTest(){
        Vector<String> vector = new Vector<>(List.of("1","2","3","4","5"));

        // Размер Vector
        int capacity = vector.capacity();

        // Удаляет лишнюю емкость, сохраняет место только для текущих элементов
        vector.trimToSize();

        // Получение Enumeration
        Enumeration<String> elements = vector.elements();

        // Увеличение емкости на 22 позиции
        vector.ensureCapacity(22);

        //Фиксирует размер Vectore, при этом лишние элементы удаляет, а если больше элементов, заполняет их null
        vector.setSize(5);

        // Возвращает массив элементов из Vectore
        String[] objects = (String[]) vector.toArray();

    }

    // listIterator
    @Test
    public void listIteratorTest(){
        ArrayList<String> arrayList = new ArrayList<>(List.of("1","2","3","4","5"));

        ListIterator<String> listIterator = arrayList.listIterator();

        // listIterator взаимосвязан с изначальным ArrayList, при изменении элементов в listIterator, меняется и сам ArrayList
        listIterator.next();
        listIterator.remove();

        // listIterator переход к следующего/предыдущего
        listIterator.next();
        listIterator.previous();

        // listIterator проверка существование следующего/предыдущего
        boolean hasNext = listIterator.hasNext();
        boolean hasPrevious = listIterator.hasPrevious();

        // listIterator получение индекса следующего/предыдущего
        int nextIndex = listIterator.nextIndex();
        int previousIndex = listIterator.previousIndex();

        // listIterator обход всех элементов и их изменение
        listIterator.forEachRemaining(e -> e = e + "prefix");


        //Проверка CRUD

        // Добавление элемента
        ListIterator<String> listIterator1 = Arrays.asList("1","2","3","4","5").listIterator();
        listIterator1.add("6");

        // Удаление элемента
        ListIterator<String> listIterator2 = Arrays.asList("1","2","3","4","5").listIterator();
        while (listIterator2.hasNext()){
            if(listIterator2.next().equals("3")){
                listIterator2.remove();
                break;
            }
        }

        // Изменение элемента
        ListIterator<String> listIterator3 = Arrays.asList("1","2","3","4","5").listIterator();
        while (listIterator3.hasNext()){
            if(listIterator3.next().equals("4")){
                listIterator3.set("44");
                break;
            }
        }

        // Чтение
        ListIterator<String> listIterator4 = Arrays.asList("1","2","3","4","5").listIterator();
        String read = listIterator4.next();


    }

    // Set - коллекция, список, кторая не содержит дубликаты
    @Test
    void setTest(){
        Set<String> set = new HashSet<>();

        // Попытка добавить дубли, но не получится
        set.add("1");
        set.add("1");
    }


    @Test
    void hashSetTest(){
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("1","2","3","4","5"));


    }

    @Test
    void linkedHashSet(){
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList("1","2","3","4","5"));
    }


    @Test
    void treeSetTest(){

        // Создание Comparator из лямбды
        Comparator<String> comparator = (o1, o2) -> ((String)o2).compareTo(((String)o1));

        //TreeSet без сортировки
        SortedSet<String> defaultTreeSet = new TreeSet<>(List.of("1","2","3","4","5"));

        //Кастомный способ сортировки для строк, в обратном порядке
        SortedSet<String> treeSet = new TreeSet<>((o1, o2) -> ((String)o2).compareTo(((String)o1)));
        treeSet.addAll(List.of("1","2","3","4","5"));
        ArrayList<String> arrayList = new ArrayList<>(treeSet);

        // Сортировка TreeSet

        //Сортировать в обратном порядке
        SortedSet<String> treeSetReverse = new TreeSet<>(Comparator.reverseOrder());
        treeSetReverse.addAll(List.of("1","2","3","4","5"));

        //Сортировка по полю объекта в обратном порядке
        SortedSet<Thread> threads = new TreeSet<>((o1, o2) -> ((String)o2.getName()).compareTo(((String)o1.getName())));
        for(int i = 0; i < 8; i++){
            Thread thread = new Thread(() -> {});
            thread.setName("A" + i);
            threads.add(thread);
        }
        // threads[{name = A7},{name = A6},{name = A5},{name = A4}..]

    }


    //LinkedBlockingQueue - работа с потоками и сравнение с LinkedList
    @Test
    void LinkedBlockingQueueTest(){

        //Пример работы с потоками
        BlockingQueue<Integer> boundedQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> boundedQueue2 = new LinkedBlockingQueue<>();

        // Добавляем 40 000 элементов в очередь 1
        for(int i = 0; i < 40000; i++){
            boundedQueue.add(i);
        }

        // Запускаем 8 потоков по передачи элементов из одной очереди в другую
        ExecutorService executorService1 = Executors.newFixedThreadPool(8);
        for(int i = 0; i < 8; i++){
            Runnable runnable = () -> {
                while (boundedQueue.peek() != null){
                    Integer elem = null;
                    try {
                        elem = boundedQueue.take(); // замораживает поток в случае остановки
                        boundedQueue2.put(elem); // замораживает поток в случае остановки
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService1.execute(runnable);
        }

        // Ждем когда очередь закончится и потоки замораживаются
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Добавляем новые элементы в очередь при этом потоки сразу начинают их обработку
        for(int i = 0; i < 40000; i++){
            boundedQueue.add(i);
        }

        //Опять ждем когда очередь элементов закончится и потоки заморозятся
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Попытка работать с несинхронной Queue, результат будет долгим
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        for(int i = 0; i < 1000; i++){
            queue.add(i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for(int i = 0; i < 8; i++){
            Runnable runnable = () -> {
                while (queue.peek() != null){
                    queue2.add(queue.poll());
                }
            };
            executorService.execute(runnable);
        }

        try {
            TimeUnit.SECONDS.sleep(1);
            executorService.shutdown();
            executorService1.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    // concurrentLinkedQueue - демонстрация работы
    @Test
    void concurrentLinkedQueueTest(){
        // Создаем очереди concurrentLinkedQueue
        Queue<Integer> concurrentLinkedQueue1 = new ConcurrentLinkedQueue<>();
        Queue<Integer> concurrentLinkedQueue2 = new ConcurrentLinkedQueue<>();

        // Запускаем производителей, которые каждые 100 млс будут производить
        ExecutorService producers = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Runnable producer = () -> {
                while (true){
                    concurrentLinkedQueue1.add(22222222);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            producers.execute(producer);
        }

        // Запускаем потребителей, которые будут возвращать null, но когда будет номер будет возвращать номер
        ExecutorService customers = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++){
            Runnable customer = () -> {
                while (true){
                    System.out.println(concurrentLinkedQueue1.poll());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            customers.execute(customer);
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    // ArrayBlockingQueue - Демонстрация работы
    @Test
    void arrayBlockingQueueTest(){
        BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
        BlockingQueue<Integer> arrayBlockingQueue2 = new ArrayBlockingQueue<>(100);

        ExecutorService producers = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++){
            Runnable producer = () -> {
                while (true){
                    try {
                        arrayBlockingQueue.put(222222);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            producers.execute(producer);
        }

        ExecutorService customers = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++){
            Runnable customer = () -> {
                while (true){
                    try {
                    Integer elem = arrayBlockingQueue.take();
                    System.out.println(elem);
                    arrayBlockingQueue2.put(elem);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            customers.execute(customer);
        }


        try {
            TimeUnit.SECONDS.sleep(3);
            producers.shutdown();
            customers.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    void priorityBlockingQueue() throws InterruptedException {
        BlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<Integer>(10000, (o1, o2) -> ((Integer) o2).compareTo(((Integer) o1)));
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();

        //Производители, которые добавляют случайные числа от 1-го до 10
        ExecutorService producers = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            Runnable producer = ()->{
                while (true){
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
                    try {
                        priorityBlockingQueue.put(randomNum);
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            };
            producers.execute(producer);
        }
        TimeUnit.SECONDS.sleep(5);
        producers.shutdown();
        TimeUnit.SECONDS.sleep(5);


        //Производители, которые добавляют случайные числа от 1-го до 10
        ExecutorService customers = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            Runnable customer = ()->{
                while (true) {
                    try {
                        Integer elem = priorityBlockingQueue.take();
                        System.out.println(elem);
                        blockingQueue.put(elem);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            customers.execute(customer);
        }

        TimeUnit.SECONDS.sleep(10);
        customers.shutdown();

    }

    // Работа с LinkedList как с queue
    @Test
    void linkedListQueueTest(){
        Queue<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            linkedList.add(i);
        }

        //Getting an element without removing, if queue is empty returns exception
        Integer element = linkedList.element();

        //Getting an element without removing, if queue is empty returns null
        Integer peek = linkedList.peek();

        //Additing an element if it's possible else false
        linkedList.offer(1001);

        //Additing an element if it's possible else exception
        linkedList.add(1002);

        // Getting an element with deleting from queue, if empty returns null
        Integer poll = linkedList.poll();

        //Bypass all elements without deleting
        for (Integer i : linkedList) {
            System.out.println(i);
        }

        Queue<Integer> linkedList2 = new LinkedList<>();
        //With deleting elements array traversing
        while (!linkedList.isEmpty()){
            linkedList2.add(linkedList.poll());
        }

    }


    // Все очереди с deque
    @Test
    void duqueTest() throws InterruptedException {

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arrayList.add(i);
        }

        //Deque Initialization
        Deque<Integer> arrayDeque = new ArrayDeque<>(arrayList);
        Deque<Integer> linkedList = new LinkedList<>(arrayList);

        // MultiThreading
        Deque<Integer> concurrentLinkedDeque = new ConcurrentLinkedDeque<>(arrayList);
        BlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<>(arrayList);

        //Additing at first or at last. If possible - true else exception
        arrayDeque.addLast(9999);
        arrayDeque.addFirst(8888);

        //Additing an elements at first or at last. If possible - true else - false
        arrayDeque.offerFirst(2222);
        arrayDeque.offerLast(1111);

        // Getting from first or from end without removing. If possible - true else - exception
        Integer getFirst = arrayDeque.getFirst();
        Integer getLast = arrayDeque.getLast();

        // Getting from first or from end without deleting. If it's empty returns null
        Integer peekFirst = arrayDeque.peekFirst();
        Integer peekLast = arrayDeque.peekLast();

        // Getting from first or from end with deleting. If it's empty returns null
        Integer pollFirst = arrayDeque.pollFirst();
        Integer pollLast = arrayDeque.pollLast();

        // Getting elements from beginning and ending
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 4; i++) {
            Runnable runnableFirst = () -> {
                while (true){
                    try {
                        System.out.println(linkedBlockingDeque.takeFirst());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnableFirst);

            Runnable runnableLast = () -> {
                while (true) {
                    try {
                        System.out.println(linkedBlockingDeque.takeLast());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnableLast);
        }
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdown();
    }



    class DelayObject implements Delayed {

        private String name;
        private long time;

        // Constructor of DelayObject
        public DelayObject(String name, long delayTime)
        {
            this.name = name;
            this.time = System.currentTimeMillis()
                    + delayTime;
        }

        // Implementing getDelay() method of Delayed
        @Override
        public long getDelay(TimeUnit unit)
        {
            long diff = time - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        // Implementing compareTo() method of Delayed
        @Override
        public int compareTo(Delayed obj)
        {
            if (this.time < ((DelayObject)obj).time) {
                return -1;
            }
            if (this.time > ((DelayObject)obj).time) {
                return 1;
            }
            return 0;
        }
    }


    @Test
    void delayQueueTest() throws InterruptedException {
        BlockingQueue<DelayObject> delayQueue = new DelayQueue<>();

        delayQueue.add(new DelayObject("Test 500",500L));
        delayQueue.add(new DelayObject("Test 3000",3000L));
        delayQueue.add(new DelayObject("Test 100",100L));
        delayQueue.add(new DelayObject("Test 2000",2000L));
        delayQueue.add(new DelayObject("Test 6000",6000L));
        delayQueue.add(new DelayObject("Test 2000",2000L));

        Thread thread = new Thread(()->{
            while (true){
                try {
                    System.out.println(delayQueue.take().name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    void delayQueueSecondTest() throws InterruptedException {

        DelayObject delayObject = new DelayObject("Test 500",500L);

        BlockingQueue<DelayObject> blockingQueue = new DelayQueue<>();
        blockingQueue.add(new DelayObject("Test 500",500L));
        blockingQueue.add(new DelayObject("Test 2000",2000L));
        blockingQueue.add(new DelayObject("Test 1000",1000L));
        blockingQueue.add(new DelayObject("Test 5000",5000L));

        DelayQueue<DelayObject> delayQueue = new DelayQueue<>();

        while (true){
            System.out.println(blockingQueue.take().name);
        }

    }



    @Test
    void sharedVariable() throws InterruptedException {

        // Мы не можем расширить простые переменные между потоками:
        List<Integer> sharedArray = new ArrayList<>(List.of(2));
        Runnable runnable = ()->{
            Integer variable = sharedArray.get(0);
            System.out.println("We have this value" + variable);
            sharedArray.set(0,99);
        };
        (new Thread(runnable)).start();
        (new Thread(runnable)).start();
        //------Конец

        // Using shared Variable:
        AtomicInteger atomicInteger = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Integer secondInteger = 9;

        Runnable producer = ()->{
            Integer variable = 33;
            atomicInteger.set(variable);
            countDownLatch.countDown();
        };

        Runnable consumer = () -> {
            try {
                System.out.println("test1");
                countDownLatch.await();
                System.out.println("test2");
                Integer integer = atomicInteger.get();
                System.out.println(integer);

                System.out.println("test3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        (new Thread(consumer)).start();

        TimeUnit.SECONDS.sleep(10);
        (new Thread(producer)).start();





    }



    // Многопоточность БЕЗ АТОМАРНОСТИ с ошибками. Если мы запустим изменение переменной в 4х циклах мы получим случаи значение отличное от ожидаемого, так как нет блокировки ресурса, и потоки работают одновременно
    @Test
    void sharedVariableWithoutBlocking() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger();
        integer.set(0);
        Runnable runnable = ()->{
            for (int i = 0; i < 50000; i++) {
                integer.set(integer.get() + 1);

            }
            System.out.println(Thread.currentThread().getName() + " is finished");
        };
        (new Thread(runnable)).start();
        (new Thread(runnable)).start();
        (new Thread(runnable)).start();
        (new Thread(runnable)).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(integer.get());
    }

    // Пример использование атомарных переменных и атомарных операций
    @Test
    void sharedVariableThird() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(0);
        Runnable runnable = ()->{
            for (int i = 0; i < 50000; i++) {
                int current, value;
                do{
                    current = atomicInteger.get();
                    // И другие операции: Этот блок будет атомарным, т е либо выполнится полностью либо не выполнится вообще
                    value = current + 1; // integer.incrementAndGet();
                    value = value + 1;

                } while (!atomicInteger.compareAndSet(current,value)); // Проверяем, изменялась ли переменная другими потоками, если нет то изменяем значение

                // Или можем использовать уже готовые функции AtomicInteger
                //integer.incrementAndGet();
                //integer.incrementAndGet();
            }
            System.out.println(Thread.currentThread().getName() + " is finished");
        };
        (new Thread(runnable)).start();
        (new Thread(runnable)).start();
        (new Thread(runnable)).start();
        (new Thread(runnable)).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(atomicInteger.get()); // Результат должен быть 400 000
    }



    @Test
    void synchronousQueueTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        Runnable producer = () -> {
            Integer producedElement = 3333;
            try {
                synchronousQueue.put(producedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            try {
                Integer consumedElement = synchronousQueue.take();
                System.out.println(consumedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };
        executorService.execute(consumer);
        executorService.execute(producer);


    }


    @Test
    void synchronousQueueTest2() throws InterruptedException {
        SynchronousQueue<Integer> synchronousQueue1 = new SynchronousQueue<>();
        synchronousQueue1.put(1);
        synchronousQueue1.put(2);
    }


    @Test
    void transferQueueTest() throws InterruptedException {
        TransferQueue<Integer> linkedTransferQueue = new LinkedTransferQueue();

        Runnable producer = ()->{
            try {
                linkedTransferQueue.transfer(1);
                System.out.println("1 Added");
                linkedTransferQueue.transfer(2);
                System.out.println("2 Added");
                linkedTransferQueue.transfer(3);
                System.out.println("3 Added");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        Runnable consumer = ()->{
            try {
                System.out.println(linkedTransferQueue.take());
                System.out.println(linkedTransferQueue.take());
                System.out.println(linkedTransferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(producer);
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(consumer);
    }


    //Работа с HashMap и c Map
    @Test
    void mapInterfaceTest(){

        Map<String, String> associativeArray = new HashMap<>();

        // Устанавливаем элемент по ключу
        String put1 = associativeArray.put("Name", "Paul"); // null
        String put = associativeArray.put("Last Name", "Petrov"); // null
        String previousName = associativeArray.put("Name", "PaulSecond"); // Paul

        // Получение элемента по кючу
        String name = associativeArray.get("Name"); // PaulSecond
        String lastName = associativeArray.get("Last Name"); // Petrov
        String aNull = associativeArray.get("Null"); // null

        // Проверка наличие ключа
        boolean isName = associativeArray.containsKey("Name"); // true

        Collection<String> values = associativeArray.values();

        // Заменяет значение ключа согласно лямбда выражению
        associativeArray.compute("Name",(k,v)->v+" prefix key: "+k);

        // Установить значение для ключа если отсутствует элемент
        associativeArray.computeIfAbsent("Name",(k)->"key: "+k);
        associativeArray.computeIfAbsent("NameWithoutValue",(k)->"key: "+k);

        // Установить значение для ключа если присутствует элемент
        associativeArray.compute("Last Name",(k,v)->v+" prefix2 key: "+k);


        // Вставка элемента, если элемента нету, вставляем "newValue", если значение для элемента есть выполняем лямбда выражение
        Map<String, String > map1 = new HashMap<>(Map.of("k1","v1","k2","v2","k3","v3"));

        map1.merge("k1", "new val",(oldValue, newValue) -> newValue + " - old value(" + oldValue + ")");
        map1.merge("k4", "new val k4",(oldValue, newValue) -> newValue + "-new val k4");

        // Получение set ключей
        Set<String> set = map1.keySet();

        // Преобразуем map в Set c Entry
        Set<Map.Entry<String, String>> entries = map1.entrySet();

        //Вставка если отсутствует элемента
        map1.putIfAbsent("k1","Test"); // ничего не произойдет т к элемент есть
        map1.putIfAbsent("New Key","new value"); // добавить новый элемент

        // Добавить все элементы из другой map
        map1.putAll(associativeArray);

        //Заменит все элементы согласно функции
        map1.replaceAll((k,v)->v+" new prefix");

        Map<String, String > map = new HashMap<>(Map.of("k1","v1","k2","v2","k3","v3"));

        // Обход элементов массива, обход элементов ассоциативного массива 1
        System.out.println("Via Foreach");
        for (String key: map.keySet()) {
            System.out.println("Key: " + key + " Value: " + map.get(key));
        }

        // Обход элементов массива, обход элементов ассоциативного массива 2
        System.out.println("Via Foreach 2");
        for (Map.Entry<String, String> element: map.entrySet()) {
            System.out.println("Key: " + element.getKey() + " Value: " + element.getValue());
        }

        // Обход значений массива, обход значений ассоциативного массива 3
        for (String value: map.values()) {
            System.out.println(value);
        }

        // Обход ассоциативного массива через иттератор 4
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> element = iterator.next();
            System.out.println("Key: " + element.getKey() + " Value: " + element.getValue());
        }
    }



    @Test
    void hashLinkedMapTest(){
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>(Map.of("k1","v1","k2","v2","k3","v3","k0","v0"));
        //We will get {k0,k1,k2,k3..} sorted map

    }



    @Test
    void treeMapTest(){

        //Differeте constructors

        // 1. With custom Comparator
        TreeMap<String, String> treeMap1 = new TreeMap<>((e1,e2)->(e2).compareTo(e1));

        // 2. With the other map
        TreeMap<String, String> treeMap2 = new TreeMap<>(Map.of("k1", "v1","k2", "v2","k0", "v0"));


        // Возвращаем Comparator
        TreeMap<String, String> treeMap = new TreeMap<>(Map.of("k1", "v1","k2", "v2","k0", "v0"));
        Comparator<? super String> comparator = treeMap.comparator();

        // ?? Вернет часть map до указанного ключа
        TreeMap<String, String> treeMap4 = new TreeMap<>(Map.of("k1", "v1","k2", "v2","k0", "v0"));
        SortedMap<String, String> treeMap5 = treeMap4.headMap("k1"); // вернет элементы до k1
        SortedMap<String, String> treeMap44 = treeMap4.tailMap("k1"); // вернет элементы после k1

        // Получение первого и последнего Ключа, Entry, или получение с удалением
        TreeMap<String, String> treeMap6 = new TreeMap<>(Map.of("k1", "v1","k2", "v2","k0", "v0"));
        String s = treeMap6.firstKey();
        String s1 = treeMap6.lastKey();
        Map.Entry<String, String> entry = treeMap6.firstEntry();
        Map.Entry<String, String> entry1 = treeMap6.lastEntry();
        Map.Entry<String, String> entry2 = treeMap6.pollFirstEntry();
        Map.Entry<String, String> entry3 = treeMap6.pollLastEntry();

        // Получение подмножество map начиная от k1 заканчивая k2
        TreeMap<String, String> treeMap7 = new TreeMap<>(Map.of("k1", "v1","k2", "v2","k0", "v0","k3", "v3"));
        SortedMap<String, String> stringStringSortedMap = treeMap7.subMap("k1", "k2");

        TreeMap<String, String> treeMap8 = new TreeMap<>(Map.of("k1", "v1","k2", "v2","k0", "v0","k3", "v3"));

        String k1 = treeMap8.floorKey("k11"); // k1 вернет ближайший ключ или равный ближе к началу
        String k11 = treeMap8.ceilingKey("k11"); // k2 вернет ближайший или равный ключ к концу
        String k12 = treeMap8.higherKey("k1"); // k2 Вернет следующий ключ
        String k13 = treeMap8.lowerKey("k1");// k0 Вернет предыдущий ключ
    }


    @Test
    void hashTableTest() throws InterruptedException {
        Hashtable<String, String> hashtable = new Hashtable<>();

        Runnable producer = ()->{
            for (int i = 0; i < 1000; i++) {
                hashtable.put("k"+i,"v"+i);
            }
        };
        Runnable consumer = () ->{
            while (true){
                System.out.println(hashtable.get("k1"));
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(consumer);

        TimeUnit.SECONDS.sleep(1);

        executorService.execute(producer);

        TimeUnit.SECONDS.sleep(2);
        executorService.shutdown();


    }






}
