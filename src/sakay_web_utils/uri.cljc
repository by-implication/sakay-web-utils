(ns sakay-web-utils.uri
  (:require [clojure.string :as string]))

(defn query-string->map
  "Convert query-param-like string into a map.
  `query-string` is a string
  delimited by `&` per key-value pair,
  and by `=` between key and value.
  Does not perform any coercion, but this is accessible via parse-keyval"
  ([query-string]
   (letfn [(default-parse-keyval [key val]
             [(keyword key) (not-empty val)])]
     (query-string->map query-string default-parse-keyval)))
  ([query-string parse-keyval]
   (into {}
         (map (fn [keyval-string]
                (let [[key val] (string/split keyval-string #"=")]
                  (parse-keyval key val))))
         (string/split query-string #"&"))))

(defn query-map->string
  "Converts a map into a query string. Order is not guaranteed."
  [query-map]
  (string/join "&"
               (map (fn [[key val]]
                      (str (name key) "=" val))
                    query-map)))
