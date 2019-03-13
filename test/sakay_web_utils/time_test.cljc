(ns sakay-web-utils.time-test
  (:require
   [sakay-web-utils.time :as swu.time]
   [clojure.test :as test :refer [deftest testing is]]))

(deftest miltime->mins
  (testing "3 digits"
    (testing "without minutes"
      (is (= (swu.time/miltime->mins 900) 540)))
    (testing "with minutes"
      (is (= (swu.time/miltime->mins 930) 570))))
  (testing "4 digits"
    (testing "without minutes"
      (is (= (swu.time/miltime->mins 1500) 900)))
    (testing "with minutes"
      (is (= (swu.time/miltime->mins 1530) 930)))))
