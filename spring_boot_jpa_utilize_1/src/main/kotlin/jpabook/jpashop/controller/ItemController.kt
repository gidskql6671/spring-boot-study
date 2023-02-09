package jpabook.jpashop.controller

import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.dto.BookForm
import jpabook.jpashop.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/items")
class ItemController(
    private val itemService: ItemService
) {

    @GetMapping("/new")
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm())
        return "items/createItemForm"
    }

    @PostMapping("/new")
    fun create(form: BookForm): String {
        val book = Book(
            name = form.name,
            price = form.price,
            stockQuantity = form.stockQuantity ?: 0,
            author = form.author,
            isbn = form.isbn
        )

        itemService.saveItem(book)
        return "redirect:/"
    }
}