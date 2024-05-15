-- Recursion
fac :: (Ord t, Num t) => t -> t
fac n =
    if n <= 1 then
        1
    else 
        n * fac (n - 1)

-- Guards
facGuards :: (Ord a, Num a) => a -> a
facGuards n
    | n <= 1 = 1
    | otherwise = n * fac (n - 1)

-- Pattern Matching
isZero :: (Eq a, Num a) => a -> Bool
isZero 0 = True
isZero _ = False

-- Accumulators
facAcccumulators :: (Ord t, Num t) => t -> t
facAcccumulators n = aux n 1
    where
        aux n acc 
            | n <= 1 = acc
            | otherwise = aux (n - 1) (n * acc)