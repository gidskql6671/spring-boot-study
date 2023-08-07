package hello.itemservice.domain.item

import org.springframework.stereotype.Repository


@Repository
class ItemRepository {
    companion object {
        private var store = HashMap<Long, Item>()
        private var sequence = 0L
    }

    fun save(item: Item): Item {
        item.id = ++sequence
        store[item.id!!] = item
        return item
    }

    fun findById(id: Long): Item = store[id]!!

    fun findAll(): List<Item> = ArrayList(store.values)

    fun update(itemId: Long, updateParam: Item) {
        val findItem = findById(itemId)

        findItem.itemName = updateParam.itemName
        findItem.price = updateParam.price
        findItem.quantity = updateParam.quantity
    }

    fun clearStore() {
        store.clear()
    }
}