local key = "rate.limit:" .. KEYS[1]

local limit = tonumber(ARGV[1])

local current = tonumber(redis.call('get', key) or "0")

if current + 1 > limit then
  return 0
else
   -- 没有超阈值，将当前访问数量+1，并设置2秒过期（可根据自己的业务情况调整）
   redis.call("INCRBY", key,"1")
   redis.call("expire", key,"2")
   return current + 1
end