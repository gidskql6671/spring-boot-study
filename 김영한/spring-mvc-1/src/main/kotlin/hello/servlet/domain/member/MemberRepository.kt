package hello.servlet.domain.member

class MemberRepository private constructor() {
    companion object {
        private var store = HashMap<Long, Member>()
        private var sequence = 0L;
        private val instance = MemberRepository()

        fun getInstance() = instance
    }


    fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member
        return member
    }

    fun findById(memberId: Long): Member = store[memberId]!!

    fun findAll(): List<Member> = ArrayList(store.values)

    fun clearStore() {
        store.clear()
    }
}