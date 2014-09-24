class KnuthShuffle

    def self.shuffle(array)
        array.each_with_index do |item, index|
            r = Random.rand(0..index) # '..' means both inclusive
            exchange(array, index, r)
        end
    end

    private
    def self.exchange(a, i, j)
        tmp = a[i]
        a[i] = a[j]
        a[j] = tmp
    end

end

a = [1, 2, 3, 4, 5, 6]
KnuthShuffle.shuffle(a)
print a
