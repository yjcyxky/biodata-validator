(ns biodata-validator.specs-test
  (:require [clojure.test :refer :all]
            [biodata-validator.specs :as specs]))


(deftest test-valid-url?
  (testing "Valid urls."
    (is (true? (specs/valid-url? "http://example.com")))
    (is (true? (specs/valid-url? "https://example.com")))
    (is (true? (specs/valid-url? "ftp://example.com")))
    (is (true? (specs/valid-url? "oss://example.com"))))
  (testing "Invalid urls."
    (is (false? (specs/valid-url? "http:/example.com")))
    (is (false? (specs/valid-url? "ftp://example")))))

(deftest test-valid-age?
  (testing "Valid age"
    (is (true? (specs/valid-age? 0.1)))
    (is (true? (specs/valid-age? 10)))
    (is (true? (specs/valid-age? 120))))
  (testing "Invalid age"
    (is (false? (specs/valid-age? 0)))
    (is (false? (specs/valid-age? 0.0)))
    (is (false? (specs/valid-age? -1)))))

(deftest test-valid-gender?
  (testing "Valid gender"
    (is (true? (specs/valid-gender? "f")))
    (is (true? (specs/valid-gender? "F")))
    (is (true? (specs/valid-gender? "Female")))
    (is (true? (specs/valid-gender? "FEMALE"))))
  (testing "Invalid gender"
    (is (false? (specs/valid-gender? "0")))
    (is (false? (specs/valid-gender? "1")))
    (is (false? (specs/valid-gender? "男"))))
  (testing "Valid gender number"
    (is (true? (specs/valid-gender-number? 0)))
    (is (true? (specs/valid-gender-number? 1))))
  (testing "Invalid gender number"
    (is (false? (specs/valid-gender-number? 0.0)))
    (is (false? (specs/valid-gender-number? -1)))))

(deftest test-valid-hgnc-id?
  (testing "Valid gender"
    (is (true? (specs/valid-hgnc-id? "HGNC:5")))
    (is (true? (specs/valid-hgnc-id? "HGNC:41523")))
    (is (true? (specs/valid-hgnc-id? "HGNC:41022")))
    (is (true? (specs/valid-hgnc-id? "HGNC:27057"))))
  (testing "Invalid gender"
    (is (false? (specs/valid-hgnc-id? ":5")))
    (is (false? (specs/valid-hgnc-id? "123")))))