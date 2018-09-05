package xyz.ryabov.multiplatform

class MppPresenter {
  private var view: MppView? = null

  fun inputProvided(value: String?, dpiIndex: Int) {
    val newValues = processNewValue(value, dpiIndex)
    view?.render(newValues)
  }

  fun bind(view: MppView) {
    this.view = view
  }

  fun unbind() {
    this.view = null
  }
}
