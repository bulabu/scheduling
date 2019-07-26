package domain

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

@PlanningSolution
class Solution {
    private List<Room> roomList;

    private List<Child> childList;

    private HardSoftScore score;

    @ValueRangeProvider(id = "roomList")
    @ProblemFactCollectionProperty
    List<Room> getRoomList() {
        return roomList
    }

    @PlanningEntityCollectionProperty
    List<Child> getChildList() {
        return childList
    }

    @PlanningScore
    HardSoftScore getScore() {
        return score
    }

    void setScore(HardSoftScore score) {
        this.score = score
    }
}
