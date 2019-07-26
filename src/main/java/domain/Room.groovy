package domain

class Room {
    String name
    int capacity
    int minAge
    int maxAge
    Boolean waitlistRoom

    static List<Room> allRooms

    Room(String name, int capacity, int minAge, int maxAge, Boolean waitlistRoom) {
        this.capacity = capacity
        this.minAge = minAge
        this.maxAge = maxAge
        this.waitlistRoom = waitlistRoom

        allRooms.add(this)
    }

    static getWaitlistRoom(){
        return allRooms.find{r -> r.waitlistRoom}
    }
}
