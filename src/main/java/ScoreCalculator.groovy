import domain.Child
import domain.Room
import domain.Solution
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator

class ScoreCalculator implements EasyScoreCalculator<Solution> {

    HardSoftScore calculateScore(Solution solution) {
        int hardScore = 0
        int softScore = 0
        for (Room room : solution.getRoomList()) {
            int capacity = room.capacity
            int enrollment = 0
            int waitlistedChildren = 0
            for(Child child : solution.getChildList()){
                int age = child.getAge()
                if(child.room.equals(room)){
                    enrollment++
                }
                if(age > room.minAge && age < room.maxAge && !(child.room)){
                    waitlistedChildren--
                }
            }
            int unusedCapacity = capacity - enrollment
            hardScore -= unusedCapacity
            softScore += waitlistedChildren
        }
        return HardSoftScore.valueOf(hardScore, softScore)
    }

}