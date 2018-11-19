package lesson3

import java.util.*
import kotlin.NoSuchElementException

class KtBinaryTree<T : Comparable<T>>() : AbstractMutableSet<T>(), CheckableSortedSet<T> {

    private constructor(node: Node<T>?, fromElement: T?, toElement: T?) : this() {
        this.root = node
        this.first = fromElement
        this.last = toElement
    }

    private var root: Node<T>? = null

    private var first: T? = null
    private var last: T? = null


    override var size = 0
        private set
        get() {
            var result = 0
            for (node in this) {
                if (isValueCorrect(node))
                    result++
            }
            return result
        }

    class Node<T>(val value: T) {

        var parent: Node<T>? = null

        var left: Node<T>? = null

        var right: Node<T>? = null
    }

    override fun add(element: T): Boolean {
        val closest = find(element)
        val comparison = if (closest == null) -1 else element.compareTo(closest.value)
        if (comparison == 0) {
            return false
        }
        val newNode = Node(element)
        newNode.parent = closest
        when {
            closest == null -> root = newNode
            comparison < 0 -> {
                assert(closest.left == null)
                closest.left = newNode
            }
            else -> {
                assert(closest.right == null)
                closest.right = newNode
            }
        }
        return true
    }

    override fun checkInvariant(): Boolean =
            root?.let { checkInvariant(it) } ?: true

    private fun checkInvariant(node: Node<T>): Boolean {
        val left = node.left
        if (left != null && (left.value >= node.value || !checkInvariant(left))) return false
        val right = node.right
        return right == null || right.value > node.value && checkInvariant(right)
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    override fun remove(element: T): Boolean {
        TODO()
    }

    override operator fun contains(element: T): Boolean {
        val closest = find(element)
        return closest != null && element.compareTo(closest.value) == 0
    }

    private fun find(value: T): Node<T>? {
        if (last != null && last!! <= value) return null
        if (first != null && first!! > value) return null
        return root?.let { find(it, value) }
    }

    private fun find(start: Node<T>, value: T): Node<T> {
        val comparison = value.compareTo(start.value)
        return when {
            comparison == 0 -> start
            comparison < 0 -> start.left?.let { find(it, value) } ?: start
            else -> start.right?.let { find(it, value) } ?: start
        }
    }

    private fun Node<T>?.replaceChild(node: Node<T>, newNode: Node<T>?) {
        newNode?.parent = this
        when {
            this == null -> root = newNode
            this.left?.value?.compareTo(node.value) == 0 -> this.left = newNode
            else -> this.right = newNode
        }
    }

    private fun isValueCorrect(element: T): Boolean = when {
        (last != null && last!! <= element) -> false
        (first != null && first!! > element) -> false
        else -> true
    }


    inner class BinaryTreeIterator : MutableIterator<T> {

        private var current: Node<T>? = null
        private var stack: Stack<Node<T>> = Stack()

        init {
            var node: Node<T>? = root
            while (node != null) {
                stack.push(node)
                node = node.left
            }
        }

        /**
         * Поиск следующего элемента
         * Средняя
         * Трудоемкость алгоритма: О(n), n - высота дерева.
         * Ресурсоемкость алгоритма: O(N), N - количество всех элементов.
         */
        private fun findNext(): Node<T>? {
            if (stack.empty()) return null
            var node: Node<T>? = stack.pop()
            val result = node
            if (node!!.right != null) {
                node = node.right
                while (node != null) {
                    stack.push(node)
                    node = node.left
                }
            }
            return result
        }

        override fun hasNext(): Boolean = when {
            stack.isEmpty() -> false
            last != null && last!! <= stack.peek().value -> false
            else -> true
        }

        override fun next(): T {
            current = findNext()
            return (current ?: throw NoSuchElementException()).value
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        override fun remove() {
            TODO()
        }
    }

    override fun iterator(): MutableIterator<T> = BinaryTreeIterator()

    override fun comparator(): Comparator<in T>? = null

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     * Трудоемкость алгоритма: О(n), n - высота левого дерева.
     * Ресурсоемкость алгоритва: O(N), N - количество элементов в дереве.
     */
    override fun subSet(fromElement: T, toElement: T): SortedSet<T> {
        var node = root
        while (node?.value!! < toElement && node.left != null) {
            node = node.left!!
        }
        return KtBinaryTree<T>(root, fromElement, toElement)
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     * Трудоемкость алгоритма: О(n), n - высота левого дерева.
     * Ресурсоемкость алгоритва: O(N), N - количество элементов в дереве.
     */
    override fun headSet(toElement: T): SortedSet<T> {
        var node = root
        while (node?.value!! < toElement && node.left != null) {
            node = node.left!!
        }
        return KtBinaryTree<T>(root, null, toElement)
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     * Трудоемкость алгоритма: О(1)
     * Ресурсоемкость алгоритва: O(N), N - количество элементов в дереве.
     */
    override fun tailSet(fromElement: T): SortedSet<T> {
        return KtBinaryTree<T>(root, fromElement, null)
    }

    override fun first(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    override fun last(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.right != null) {
            current = current.right!!
        }
        return current.value
    }
}
