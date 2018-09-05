package xyz.ryabov.multiplatform.js

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.asList
import xyz.ryabov.multiplatform.MppPresenter
import xyz.ryabov.multiplatform.MppView
import kotlin.browser.document

fun main(args: Array<String>) {
  View("#main-form input").bind()
}

class View(viewQuery: String) : MppView {
  @Suppress("UNCHECKED_CAST")
  private val inputs = document.querySelectorAll(viewQuery).asList() as List<HTMLInputElement>
  private val presenter = MppPresenter()

  init {
    inputs.forEachIndexed { i, input ->
      input.addEventListener("keyup", { onUpdate(i) })
      input.addEventListener("change", { onUpdate(i) })
    }
  }

  fun bind() {
    presenter.bind(this)
  }

  private fun onUpdate(index: Int) {
    val srcInput = inputs[index]

    presenter.inputProvided(srcInput.value, index)
  }

  override fun render(values: Array<Float?>) {
    values.forEachIndexed { i, value ->
      set(inputs[i], value?.toString().orEmpty())
    }
  }

  private fun set(input: HTMLInputElement, value: String) {
    input.value = value
  }
}
