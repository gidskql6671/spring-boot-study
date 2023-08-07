package dong.kotlin_study.common.annotation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class TelephoneValidator: ConstraintValidator<Telephone, String> {
    private val regex = Regex("01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value?.matches(regex) ?: false
    }
}