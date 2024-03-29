package hello.advanced.trace;

import java.util.UUID;

public class TraceId {

    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    public TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // ab99e17f-3cde-4d24-8273-274838283x8e8// 생성된 UUID
        // ab99e17f // 앞 8자리만 사용
        return UUID.randomUUID().toString().substring(0,8);
    }
    public TraceId createNextId() {
        return new TraceId(id, level+1);
    }
    public TraceId createPreviousId() {
        return new TraceId(id, level-1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

}
