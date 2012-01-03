(ns woven.test.util
  (:use [woven.util :only [split-and-trim-lines]])
  (:use [clojure.test]))

(deftest split-trim-test
  (is (= ["test"] (split-and-trim-lines "test")))
  (is (= ["test"] (split-and-trim-lines "  test  ")))
  (is (= ["test" "bleh"] (split-and-trim-lines "test\nbleh")))
  (is (= ["test" "bleh"] (split-and-trim-lines " test  \n   bleh "))))
