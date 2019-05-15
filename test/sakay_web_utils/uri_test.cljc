(ns sakay-web-utils.uri-test
  (:require
   [sakay-web-utils.uri :as uri]
   [clojure.test :as test :refer [deftest testing is]]))

(deftest query-string->map
  (testing "Normal query string"
    (is (= (uri/query-string->map "a=1&b=2&c=3") {:a "1" :b "2" :c "3"})))
  (testing "Missing val"
    (is (= (uri/query-string->map "a=1&b&c=3") {:a "1" :b nil :c "3"}))))

(deftest query-map->string
  (testing "Normal query-map"
    (is (= (uri/query-map->string {:a 1 :b 2 :c 3}) "a=1&b=2&c=3"))))
