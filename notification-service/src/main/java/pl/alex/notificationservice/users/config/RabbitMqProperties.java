package pl.alex.notificationservice.users.config;

import lombok.Data;

@Data
class RabbitMqProperties {
    private UserQueueProperties user;

    public String getDltExchange() {
        return user.getDlt().getExchange();
    }

    public String getUsersDltKey() {
        return user.getDlt().getKey();
    }

    public String getUsersDltQueue() {
        return user.getDlt().getQueue();
    }

    public String getUsersAllRoutingKey() {
        return user.getAll().getKey();
    }

    public String getUsersAllExchange() {
        return user.getExchange();
    }

    public String getUsersAllQueue() {
        return user.getAll().getQueue();
    }

    public Integer getDtlTtl() {
        return user.dlt.ttl;
    }

    @Data
    static class UserQueueProperties {

        private String group;
        private String exchange;
        private AllUsersQueue all;
        private DltUsersQueue dlt;

    }

    @Data
    static class AllUsersQueue {

        private String queue;

        private String key;

    }

    @Data
    static class DltUsersQueue {

        private String queue;

        private String exchange;

        private String key;

        public Integer ttl;
    }
}
