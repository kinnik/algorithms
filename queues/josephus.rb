#!/usr/bin/env ruby


def eliminate(m, n)

  queue = []
  (0...n).each {|x| queue.push x} # … means n is excluded

  count = 0
  while !queue.empty?
      examined = queue.shift
      count += 1

      if (count % m != 0)
          queue.push examined
      else
          print "#{examined} "
      end
  end

  puts

end


def validate(m, n)
  raise ArgumentError, "M must be positive" unless m > 0
  raise ArgumentError, "N must be positive" unless n > 0
  raise ArgumentError, "N must be greater than or equal to M" unless n >= m
end


M = Integer(ARGV[0])
N = Integer(ARGV[1])

raise ArgumentError, "Format: #{File.basename($0)} M N where M is the Mth position that will be eliminated and N is the number of people" unless ARGV.size == 2

validate(M, N)
eliminate(M, N)
