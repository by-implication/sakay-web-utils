(ns sakay-web-utils.time
  (:require
   [cuerdas.core :as cuerdas]
   [time.core :as time]))

(defn miltime->mins
  "Miltime can be represented as an integer, but it isn't quite as useful as
  X minutes from midnight. So turn it into X minutes from midnight."
  [miltime]
  (let [padded-miltime (cuerdas/pad (str miltime) {:length 4 :padding "0"})
        hours          (cuerdas/parse-int (subs padded-miltime 0 2))
        minutes        (cuerdas/parse-int (subs padded-miltime 2 4))]
    (+ minutes (* hours 60))))
