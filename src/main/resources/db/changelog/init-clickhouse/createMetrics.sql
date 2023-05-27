create table if not exists metrics(
    id UUID default generateUUIDv4(),
    user_id UUID,
    metric_name String,
    value String,
    created_at BIGINT default toUnixTimestamp(now())
) engine MergeTree() order by id;