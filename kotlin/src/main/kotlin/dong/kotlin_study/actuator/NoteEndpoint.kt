package dong.kotlin_study.actuator

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation
import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation
import org.springframework.stereotype.Component

@Component
@Endpoint(id = "note", enableByDefault = true)
class NoteEndpoint {

    private val noteContent = HashMap<String, Any>()

    @ReadOperation
    fun getNote() = noteContent

    @WriteOperation
    fun writeNote(key: String, value: Any): Map<String, Any> {
        noteContent[key] = value
        return noteContent
    }

    @DeleteOperation
    fun deleteNote(key: String): Map<String, Any> {
        noteContent.remove(key)
        return noteContent
    }
}