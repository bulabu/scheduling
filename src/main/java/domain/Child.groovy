package domain

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.domain.variable.PlanningVariable

@PlanningEntity
class Child {
    private int id
    private int age
    private Room room
    Boolean currentlyEnrolled

    Child(int id, int age, Boolean currentlyEnrolled) {
        this.id = id
        this.age = age
        this.currentlyEnrolled = currentlyEnrolled
        if(!currentlyEnrolled){
            this.room = Room.getWaitlistRoom()
        }
    }

    int getAge() {
        return age
    }

    void setAge(int age) {
        this.age = age
    }

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    Boolean getCurrentlyEnrolled() {
        return currentlyEnrolled
    }

    void setCurrentlyEnrolled(Boolean currentlyEnrolled) {
        this.currentlyEnrolled = currentlyEnrolled
    }

    @PlanningVariable(valueRangeProviderRefs = {"ageRooms"})
    Room getRoom() {
        return room
    }

    @ValueRangeProvider(id = "ageRooms")
    public List<Room> getPossibleRoomList() {
        Set rooms = [room]
        rooms.addAll(Room.allRooms.findAll{r -> age <= r.maxAge && age >= r.minAge})
        if(!currentlyEnrolled){
            rooms.add(room.waitlistRoom)
        }
        return rooms.toList()
    }

    void setRoom(Room room) {
        this.room = room
        this.room.addToChildren(this)
    }
}
