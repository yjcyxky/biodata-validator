(ns biodata-validator.validator-test
  (:require [clojure.test :refer :all]
            [biodata-validator.validator :as v]))


(deftest test-valid-url?
  (testing "Valid urls."
    (is (true? (v/valid-url? "http://example.com")))
    (is (true? (v/valid-url? "https://example.com")))
    (is (true? (v/valid-url? "ftp://example.com")))
    (is (true? (v/valid-url? "oss://example.com"))))
  (testing "Invalid urls."
    (is (false? (v/valid-url? "http:/example.com")))
    (is (false? (v/valid-url? "ftp://example")))))

(deftest test-valid-age?
  (testing "Valid age"
    (is (true? (v/valid-age? 0.1)))
    (is (true? (v/valid-age? 10)))
    (is (true? (v/valid-age? 120))))
  (testing "Invalid age"
    (is (false? (v/valid-age? 0)))
    (is (false? (v/valid-age? 0.0)))
    (is (false? (v/valid-age? -1)))))

(deftest test-valid-gender?
  (testing "Valid gender"
    (is (true? (v/valid-gender? "f")))
    (is (true? (v/valid-gender? "F")))
    (is (true? (v/valid-gender? "Female")))
    (is (true? (v/valid-gender? "FEMALE"))))
  (testing "Invalid gender"
    (is (false? (v/valid-gender? "0")))
    (is (false? (v/valid-gender? "1")))
    (is (false? (v/valid-gender? "男"))))
  (testing "Valid gender number"
    (is (true? (v/valid-gender-number? 0)))
    (is (true? (v/valid-gender-number? 1))))
  (testing "Invalid gender number"
    (is (false? (v/valid-gender-number? 0.0)))
    (is (false? (v/valid-gender-number? -1)))))

(deftest test-valid-hgnc-id?
  (testing "Valid gender"
    (is (true? (v/valid-hgnc-id? "HGNC:5")))
    (is (true? (v/valid-hgnc-id? "HGNC:41523")))
    (is (true? (v/valid-hgnc-id? "HGNC:41022")))
    (is (true? (v/valid-hgnc-id? "HGNC:27057"))))
  (testing "Invalid gender"
    (is (false? (v/valid-hgnc-id? ":5")))
    (is (false? (v/valid-hgnc-id? "123")))))